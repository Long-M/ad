package com.ml.ad.service;

import com.ml.ad.exception.AdException;
import com.ml.ad.vo.*;

/**
 * @author Mr.ml
 * @date 2021/11/20
 */
public interface IAdUnitService {

    /**
     * 推广单元创建
     *
     * @param request 请求
     * @return 推广单元
     * @throws AdException 异常
     */
    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;
}
