package com.ml.ad.service.impl;

import com.ml.ad.dao.CreativeRepository;
import com.ml.ad.entity.Creative;
import com.ml.ad.exception.AdException;
import com.ml.ad.service.ICreativeService;
import com.ml.ad.vo.CreativeRequest;
import com.ml.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mr.ml
 * @date 2021/11/20
 */
@Service
public class CreativeServiceImpl implements ICreativeService {

    private final CreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreativeResponse createCreative(CreativeRequest request) throws AdException {
        Creative creative = creativeRepository.save(request.convertToEntity());
        return new CreativeResponse(creative.getId(), creative.getName());
    }

}
