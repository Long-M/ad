package com.ml.ad.service;

import com.alibaba.fastjson.JSON;
import com.ml.ad.Application;
import com.ml.ad.entity.AdPlan;
import com.ml.ad.exception.AdException;
import com.ml.ad.vo.AdPlanGetRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/11/21
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AdPlanServiceTest {

    @Autowired
    private IAdPlanService planService;

    @Test
    public void getAdPlanByIdsTest() throws AdException {
        AdPlanGetRequest request = new AdPlanGetRequest();
        request.setIds(Collections.singletonList(10L));
        request.setUserId(15L);
        List<AdPlan> adPlanList = planService.getAdPlanByIds(request);
        log.info(JSON.toJSONString(adPlanList));
    }

}
