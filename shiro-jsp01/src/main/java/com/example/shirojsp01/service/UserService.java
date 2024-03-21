package com.example.shirojsp01.service;

import com.example.shirojsp01.model.User;

/**
 * @author hanbing
 * @date 2024-03-21
 */
public interface UserService {
    /**
     * 新增用户
     * @param user user
     */
    int save(User user);

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     */
    User findByUsername(String userName);
}
