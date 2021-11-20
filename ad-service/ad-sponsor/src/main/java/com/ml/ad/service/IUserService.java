package com.ml.ad.service;

import com.ml.ad.exception.AdException;
import com.ml.ad.vo.CreateUserRequest;
import com.ml.ad.vo.CreateUserResponse;

/**
 * @author Mr.ml
 * @date 2021/11/20
 */
public interface IUserService {

    /**
     * 创建用户
     *
     * @param request 创建用户信息
     * @return 用户信息
     * @throws AdException 异常
     */
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
