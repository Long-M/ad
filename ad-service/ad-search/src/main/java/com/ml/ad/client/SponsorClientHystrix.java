package com.ml.ad.client;

import com.ml.ad.client.vo.AdPlan;
import com.ml.ad.client.vo.AdPlanGetRequest;
import com.ml.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/11/20
 */
@Component
public class SponsorClientHystrix implements SponsorClient {

    @Override
    public CommonResponse<List<AdPlan>> getAdPlans(AdPlanGetRequest request) {
        return new CommonResponse<>(-1, "eureka-client-sponsor error");
    }

}
