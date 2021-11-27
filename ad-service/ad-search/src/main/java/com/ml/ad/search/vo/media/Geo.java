package com.ml.ad.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mr.ml
 * @date 2021/11/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geo {

    /**
     * 经度
     */
    private Float latitude;
    /**
     * 维度
     */
    private Float longitude;

    private String city;
    private String province;

}
