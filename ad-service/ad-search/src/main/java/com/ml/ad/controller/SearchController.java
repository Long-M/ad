package com.ml.ad.controller;

import com.alibaba.fastjson.JSON;
import com.ml.ad.annotation.IgnoreResponseAdvice;
import com.ml.ad.client.SponsorClient;
import com.ml.ad.client.vo.AdPlan;
import com.ml.ad.client.vo.AdPlanGetRequest;
import com.ml.ad.search.ISearch;
import com.ml.ad.search.vo.SearchRequest;
import com.ml.ad.search.vo.SearchResponse;
import com.ml.ad.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Mr.ml
 * @date 2021/11/20
 */
@Slf4j
@RestController
public class SearchController {

    private final ISearch search;

    private final RestTemplate restTemplate;

    private final SponsorClient sponsorClient;

    @Autowired
    public SearchController(ISearch search, RestTemplate restTemplate, SponsorClient sponsorClient) {
        this.search = search;
        this.restTemplate = restTemplate;
        this.sponsorClient = sponsorClient;
    }

    @PostMapping("/fetchAds")
    public SearchResponse fetchAds(@RequestBody SearchRequest request) {
        log.info("ad-search: fetchAds -> {}", JSON.toJSONString(request));
        return search.fetchAds(request);
    }

    @IgnoreResponseAdvice
    @PostMapping("/getAdPlans")
    public CommonResponse<List<AdPlan>> getAdPlans(@RequestBody AdPlanGetRequest request) {
        log.info("ad-search: getAdPlans -> {}", JSON.toJSONString(request));
        return sponsorClient.getAdPlans(request);
    }

    @SuppressWarnings("all")
    @IgnoreResponseAdvice
    @PostMapping("/getAdPlansByRebbon")
    public CommonResponse<List<AdPlan>> getAdPlansByRebbon(@RequestBody AdPlanGetRequest request) {
        log.info("ad-search: getAdPlansByRebbon -> {}", JSON.toJSONString(request));
        String url = "http://eureka-client-ad-sponsor/ad-sponsor/get/adPlan";
        CommonResponse response = restTemplate.postForEntity(url, request, CommonResponse.class).getBody();
        return response;
    }

}
