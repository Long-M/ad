package com.ml.ad.search.impl;

import com.ml.ad.index.DataTable;
import com.ml.ad.index.adunit.AdUnitIndex;
import com.ml.ad.search.ISearch;
import com.ml.ad.search.vo.SearchRequest;
import com.ml.ad.search.vo.SearchResponse;
import com.ml.ad.search.vo.feature.DistrictFeature;
import com.ml.ad.search.vo.feature.FeatureRelation;
import com.ml.ad.search.vo.feature.ItFeature;
import com.ml.ad.search.vo.feature.KeywordFeature;
import com.ml.ad.search.vo.media.AdSlot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Mr.ml
 * @date 2021/11/27
 */
@Slf4j
@Service
public class SearchImpl implements ISearch {

    @Override
    public SearchResponse fetchAds(SearchRequest request) {
        // 请求的广告位信息
        List<AdSlot> adSlots = request.getRequestInfo().getAdSlots();

        // 三个Feature
        KeywordFeature keywordFeature = request.getFeatureInfo().getKeywordFeature();
        DistrictFeature districtFeature = request.getFeatureInfo().getDistrictFeature();
        ItFeature itFeature = request.getFeatureInfo().getItFeature();
        FeatureRelation relation = request.getFeatureInfo().getRelation();

        // 构造响应对象
        SearchResponse response = new SearchResponse();
        Map<String, List<SearchResponse.Creative>> adSlot2Ads = response.getAdSlot2Ads();

        for (AdSlot adSlot : adSlots) {
            Set<Long> targetUnitIdSet;

            // 根据流量类型获取初始AdUnit
            Set<Long> adUnitIdSet = DataTable.of(
                    AdUnitIndex.class
            ).match(adSlot.getPositionType());
        }

        return null;
    }

}
