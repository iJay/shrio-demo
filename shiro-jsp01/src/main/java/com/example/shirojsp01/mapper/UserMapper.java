package com.example.shirojsp01.mapper;

import com.example.shirojsp01.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author hanbing
 * @date 2024-03-21
 */
@Mapper
public interface UserMapper {
    /**
     * 新增用户
     * @param user 用户
     * @return int
     */
    int save(User user);

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return 用户
     */
    User findByUsername(@Param("userName") String userName);
}
