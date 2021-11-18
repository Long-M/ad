package com.ml.ad.dao;

import com.ml.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mr.ml
 * @date 2021/11/18
 */
public interface AdUserRepository extends JpaRepository<AdUser, Long> {

    /**
     * 根据用户名查找用户记录
     *
     * @param username 用户名
     * @return 用户信息
     */
    AdUser findByUsername(String username);

}
