package com.ml.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mr.ml
 * @date 2021/11/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitTable {

    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;

    private Long planId;

}
