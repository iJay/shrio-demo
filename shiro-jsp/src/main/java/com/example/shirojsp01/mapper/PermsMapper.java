package com.example.shirojsp01.mapper;

import com.example.shirojsp01.model.Perms;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermsMapper {
    /**
     * 根据roleIds查询权限
     * @param roleIds 角色id
     * @return 权限集合
     */
    List<Perms> findPermsByRoleIds (@Param("roleIds") List<String> roleIds);
}
