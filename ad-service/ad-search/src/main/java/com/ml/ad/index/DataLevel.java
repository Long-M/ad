package com.ml.ad.index;

import lombok.Getter;

/**
 * @author Mr.ml
 * @date 2021/11/27
 */
@Getter
public enum DataLevel {

    /**
     * 层级
     */
    LEVEL2("2", "level 2"),
    LEVEL3("3", "level 3"),
    LEVEL4("4", "level 4");
    private String level;
    private String desc;

    DataLevel(String level, String desc) {
        this.level = level;
        this.desc = desc;
    }

}
