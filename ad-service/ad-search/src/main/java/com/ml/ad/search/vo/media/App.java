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
public class App {

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用包名
     */
    private String packageName;

    /**
     * activity 名称
     */
    private String activityName;

}
