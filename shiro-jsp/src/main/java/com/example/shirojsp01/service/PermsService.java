package com.example.shirojsp01.service;

import com.example.shirojsp01.model.Perms;

import java.util.List;

/**
 * @author hanbing
 * @date 2024/03/21
 */
public interface PermsService {
    /**
     * 根据roleIds查询权限
     * @param roleIds 角色id
     * @return 权限集合
     */
    List<Perms> findPermsByRoleIds (List<String> roleIds);
}
