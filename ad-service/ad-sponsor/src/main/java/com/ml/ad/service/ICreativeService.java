package com.ml.ad.service;

import com.ml.ad.exception.AdException;
import com.ml.ad.vo.CreativeRequest;
import com.ml.ad.vo.CreativeResponse;

/**
 * @author Mr.ml
 * @date 2021/11/20
 */
public interface ICreativeService {

    /**
     * 创建创意
     *
     * @param request 请求
     * @return 创意
     * @throws AdException 异常
     */
    CreativeResponse createCreative(CreativeRequest request) throws AdException;

}
