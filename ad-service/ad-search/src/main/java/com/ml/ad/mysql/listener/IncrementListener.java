package com.ml.ad.mysql.listener;

import com.github.shyiko.mysql.binlog.event.EventType;
import com.ml.ad.mysql.constant.Constant;
import com.ml.ad.mysql.constant.OpType;
import com.ml.ad.mysql.dto.BinlogRowData;
import com.ml.ad.mysql.dto.MySQLRowData;
import com.ml.ad.mysql.dto.TableTemplate;
import com.ml.ad.sender.ISender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.ml
 * @date 2021/11/27
 */
@Slf4j
@Component
public class IncrementListener implements IListener {

    @Resource(name = "indexSender")
    private ISender sender;

    private final AggregationListener aggregationListener;

    @Autowired
    public IncrementListener(AggregationListener aggregationListener) {
        this.aggregationListener = aggregationListener;
    }

    @PostConstruct
    @Override
    public void register() {
        log.info("IncrementListener register db and table info");
        Constant.table2Db.forEach((k, v) -> aggregationListener.register(v, k, this));
    }


    @Override
    public void onEvent(BinlogRowData eventData) {
        TableTemplate table = eventData.getTable();
        EventType eventType = eventData.getEventType();

        // 包装成最后投递的数据
        MySQLRowData rowData = new MySQLRowData();
        rowData.setTableName(table.getTableName());
        rowData.setLevel(eventData.getTable().getLevel());
        OpType opType = OpType.to(eventType);
        rowData.setOpType(opType);

        // 取出模板中该操作字段对应的列表
        List<String> fieldList = table.getOpTypeFieldMap().get(opType);
        if (null == fieldList) {
            log.warn("{} not support fo {}", opType, table.getTableName());
        }

        for (Map<String, String> afterMap : eventData.getAfter()) {
            Map<String, String> map = new HashMap<>(afterMap.size());
            afterMap.forEach(map::put);
            rowData.getFieldValueMap().add(map);
        }
        sender.send(rowData);
    }

}
