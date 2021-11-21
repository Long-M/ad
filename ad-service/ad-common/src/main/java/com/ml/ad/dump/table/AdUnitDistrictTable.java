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
public class AdUnitDistrictTable {

    private Long unitId;
    private String province;
    private String city;

}
