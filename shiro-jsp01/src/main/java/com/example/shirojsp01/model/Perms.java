package com.example.shirojsp01.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hanbing
 * @date 2024-03-21
 */
@Data
public class Perms implements Serializable {

    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限url
     */
    private String url;
}
