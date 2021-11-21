package com.ml.ad.handler;

import com.ml.ad.dump.table.AdCreativeTable;
import com.ml.ad.dump.table.AdPlanTable;
import com.ml.ad.index.DataTable;
import com.ml.ad.index.IndexAware;
import com.ml.ad.index.adplan.AdPlanIndex;
import com.ml.ad.index.adplan.AdPlanObject;
import com.ml.ad.index.creative.CreativeIndex;
import com.ml.ad.index.creative.CreativeObject;
import com.ml.ad.mysql.constant.OpType;
import lombok.extern.slf4j.Slf4j;

/**
 * 索引之间存在层级的划分，也就是依赖关系的划分
 * 加载全量的索引其实是增量索引"增加"的一种特殊实现
 *
 * @author Mr.ml
 * @date 2021/11/21
 */
@Slf4j
public class AdLevelDataHandler {

    /**
     * 广告计划索引操作
     *
     * @param planTable 广告计划
     * @param type      操作方式
     */
    public static void handleLevel2(AdPlanTable planTable, OpType type) {
        AdPlanObject planObject = new AdPlanObject(planTable.getId(), planTable.getUserId(), planTable.getPlanStatus(), planTable.getStartDate(), planTable.getEndDate());
        handleBinlogEvent(
                DataTable.of(AdPlanIndex.class),
                planObject.getPlanId(),
                planObject,
                type
        );
    }

    /**
     * 创意索引计划
     *
     * @param creativeTable 创意
     * @param type          操作方式
     */
    public static void handleLevel2(AdCreativeTable creativeTable, OpType type) {
        CreativeObject creativeObject = new CreativeObject(
                creativeTable.getAdId(),
                creativeTable.getName(),
                creativeTable.getType(),
                creativeTable.getMaterialType(),
                creativeTable.getHeight(),
                creativeTable.getWidth(),
                creativeTable.getAuditStatus(),
                creativeTable.getAdUrl()
        );
        handleBinlogEvent(
                DataTable.of(CreativeIndex.class),
                creativeObject.getAdId(),
                creativeObject,
                type
        );
    }

    private static <K, V> void handleBinlogEvent(IndexAware<K, V> index, K key, V value, OpType type) {
        switch (type) {
            case ADD:
                index.add(key, value);
                break;
            case UPDATE:
                index.update(key, value);
            case DELETE:
                index.delete(key, value);
                break;
            case OTHER:
                break;
            default:
                break;
        }
    }

}
