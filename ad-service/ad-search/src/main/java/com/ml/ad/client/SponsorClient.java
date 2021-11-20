package com.ml.ad.client;

import com.ml.ad.client.vo.AdPlan;
import com.ml.ad.client.vo.AdPlanGetRequest;
import com.ml.ad.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/11/20
 */
@FeignClient(value = "eureka-client-ad-sponsor", fallback = SponsorClientHystrix.class)
public interface SponsorClient {

    /**
     * 查询广告计划集合
     *
     * @param request 请求
     * @return 广告计划集合
     */
    @RequestMapping(value = "/ad-sponsor/get/adPlan", method = RequestMethod.POST)
    CommonResponse<List<AdPlan>> getAdPlans(@RequestBody AdPlanGetRequest request);

}
