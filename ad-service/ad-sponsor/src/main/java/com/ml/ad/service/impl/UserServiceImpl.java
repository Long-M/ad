package com.ml.ad.service.impl;

import com.ml.ad.constant.Constants;
import com.ml.ad.dao.AdUserRepository;
import com.ml.ad.entity.AdUser;
import com.ml.ad.exception.AdException;
import com.ml.ad.service.IUserService;
import com.ml.ad.util.CommonUtils;
import com.ml.ad.vo.CreateUserRequest;
import com.ml.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mr.ml
 * @date 2021/11/20
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final AdUserRepository userRepository;

    public UserServiceImpl(AdUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdUser oldUser = userRepository.findByUsername(request.getUsername());

        if (oldUser != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        AdUser newUser = userRepository.save(new AdUser(request.getUsername(), CommonUtils.md5(request.getUsername())));

        return new CreateUserResponse(
                newUser.getId(), newUser.getUsername(),
                newUser.getToken(), newUser.getCreateTime(),
                newUser.getUpdateTime());
    }

}
