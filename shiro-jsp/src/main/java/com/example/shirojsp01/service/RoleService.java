package com.example.shirojsp01.service;

import com.example.shirojsp01.model.Role;

import java.util.List;

/**
 * @author hanbing
 * @date 2024-03-21
 */
public interface RoleService {
    /**
     * 根据用户名查询角色列表
     * @param username 用户名
     * @return 角色列表
     */
    List<Role> findRolesByUserName(String username);
}
