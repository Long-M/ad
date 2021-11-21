package com.ml.ad.handler;

import com.alibaba.fastjson.JSON;
import com.ml.ad.dump.table.AdCreativeTable;
import com.ml.ad.dump.table.AdCreativeUnitTable;
import com.ml.ad.dump.table.AdPlanTable;
import com.ml.ad.dump.table.AdUnitTable;
import com.ml.ad.index.DataTable;
import com.ml.ad.index.IndexAware;
import com.ml.ad.index.adplan.AdPlanIndex;
import com.ml.ad.index.adplan.AdPlanObject;
import com.ml.ad.index.adunit.AdUnitIndex;
import com.ml.ad.index.adunit.AdUnitObject;
import com.ml.ad.index.creative.CreativeIndex;
import com.ml.ad.index.creative.CreativeObject;
import com.ml.ad.index.creativeunit.CreativeUnitIndex;
import com.ml.ad.index.creativeunit.CreativeUnitObject;
import com.ml.ad.mysql.constant.OpType;
import com.ml.ad.util.CommonUtils;
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

    /**
     * 广告单元
     *
     * @param unitTable 广告单元
     * @param type      操作类型
     */
    public static void handleLevel3(AdUnitTable unitTable, OpType type) {
        AdPlanObject adPlanObject = DataTable.of(AdPlanIndex.class).get(unitTable.getPlanId());
        if (null == adPlanObject) {
            log.error("handleLevel3 found AdPlanObject error: {}", unitTable.getPlanId());
            return;
        }

        AdUnitObject adUnitObject = new AdUnitObject(unitTable.getUnitId(), unitTable.getUnitStatus(), unitTable.getPositionType(), unitTable.getPlanId(), adPlanObject);
        handleBinlogEvent(
                DataTable.of(AdUnitIndex.class),
                adUnitObject.getUnitId(),
                adUnitObject,
                type);
    }

    /**
     * 创意与广告单元关联关系
     *
     * @param creativeUnitTable 关联关系
     * @param type              类型
     */
    public static void handleLevel3(AdCreativeUnitTable creativeUnitTable, OpType type) {
        if (type == OpType.UPDATE) {
            log.error("CreateUnitIndex not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(creativeUnitTable.getUnitId());
        CreativeObject creativeObject = DataTable.of(CreativeIndex.class).get(creativeUnitTable.getAdId());

        if (null == unitObject || null == creativeObject) {
            log.error("AdCreativeUnitTable index error: {}", JSON.toJSONString(creativeUnitTable));
            return;
        }

        CreativeUnitObject creativeUnitObject = new CreativeUnitObject(creativeUnitTable.getAdId(), creativeUnitTable.getUnitId());

        handleBinlogEvent(
                DataTable.of(CreativeUnitIndex.class),
                CommonUtils.stringConcat(creativeUnitObject.getAdId().toString(), creativeUnitObject.getUnitId().toString()),
                creativeUnitObject,
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
