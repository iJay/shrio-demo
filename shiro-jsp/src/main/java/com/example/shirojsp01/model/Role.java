package com.example.shirojsp01.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hanbing
 * @date 2024-03-21
 */
@Data
public class Role implements Serializable {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;
}
