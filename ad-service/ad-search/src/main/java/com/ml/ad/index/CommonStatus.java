package com.ml.ad.index;

import lombok.Getter;

/**
 * @author Mr.ml
 * @date 2021/11/27
 */
@Getter
public enum CommonStatus {

    /**
     * 状态
     */
    VALID(1, "有效状态"),
    INVALID(0, "无效状态");

    private Integer status;
    private String desc;

    CommonStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

}
