package com.ml.ad.constant;

import lombok.Getter;

/**
 * 创意类型
 *
 * @author Mr.ml
 * @date 2021/11/13
 */
@Getter
public enum CreativeType {

    /**
     * 创意类型
     */
    IMAGE(1, "图片"),
    VIDEO(2, "视频"),
    TEXT(3, "文本");

    private int type;
    private String desc;

    CreativeType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

}
