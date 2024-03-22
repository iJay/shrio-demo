package com.example.shirojsp01.service.impl;

import com.example.shirojsp01.mapper.UserMapper;
import com.example.shirojsp01.model.User;
import com.example.shirojsp01.service.UserService;
import com.example.shirojsp01.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hanbing
 * @date 2024-03-21
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(User user) {
        // 生成随机盐    8位
        String salt = SaltUtils.getSalt(8);
        user.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        return userMapper.save(user);
    }

    @Override
    public User findByUsername(String userName) {
        return userMapper.findByUsername(userName);
    }
}
