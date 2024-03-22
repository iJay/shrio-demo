package com.example.shirojsp01.service.impl;

import com.example.shirojsp01.mapper.RoleMapper;
import com.example.shirojsp01.model.Role;
import com.example.shirojsp01.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hanbing
 * @date 2024-03-21
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRolesByUserName(String username) {
        return roleMapper.findRolesByUserName(username);
    }
}
