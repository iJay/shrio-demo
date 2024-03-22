package com.example.shirojsp01.mapper;

import com.example.shirojsp01.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author hanbing
 * @date 2024-03-21
 */
@Mapper
public interface RoleMapper {
    /**
     * 根据用户名查询角色列表
     * @param username 用户名
     * @return 角色列表
     */
    List<Role> findRolesByUserName(String username);
}
