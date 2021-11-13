package com.ml.ad.constant;

import lombok.Getter;

/**
 * 物料类型
 *
 * @author Mr.ml
 * @date 2021/11/13
 */
@Getter
public enum CreativeMaterialType {

    /**
     * 物料类型
     */
    JPG(1, "jpg"),
    BMP(2, "bmp"),

    MP4(3, "map4"),
    AVI(4, "avi"),

    TXT(5, "txt");

    private int type;
    private String desc;

    CreativeMaterialType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

}
