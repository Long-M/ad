package com.ml.ad.index.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mr.ml
 * @date 2021/11/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitDistrictObject {

    private Long unitId;
    private String province;
    private String city;

    // <String, Set<Long>>
    // province-city
}
