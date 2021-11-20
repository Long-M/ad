package com.ml.ad.index.adunit;

import com.ml.ad.index.adplan.AdPlanObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mr.ml
 * @date 2021/11/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitObject {

    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;
    private Long planId;

    private AdPlanObject adPlanObject;

    void update(AdUnitObject newAdUnitObject) {
        if (null != newAdUnitObject.getUnitId()) {
            this.unitId = newAdUnitObject.getUnitId();
        }
        if (null != newAdUnitObject.getPlanId()) {
            this.planId = newAdUnitObject.getPlanId();
        }
        if (null != newAdUnitObject.getPositionType()) {
            this.positionType = newAdUnitObject.getPositionType();
        }
        if (null != newAdUnitObject.getUnitStatus()) {
            this.unitStatus = newAdUnitObject.getUnitStatus();
        }
        if (null != newAdUnitObject.getAdPlanObject()) {
            this.adPlanObject = newAdUnitObject.getAdPlanObject();
        }
    }

}
