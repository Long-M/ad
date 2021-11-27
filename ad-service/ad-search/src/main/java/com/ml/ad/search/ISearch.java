package com.ml.ad.search;

import com.ml.ad.search.vo.SearchRequest;
import com.ml.ad.search.vo.SearchResponse;

/**
 * @author Mr.ml
 * @date 2021/11/27
 */
public interface ISearch {

    SearchResponse fetchAds(SearchRequest request);
}
