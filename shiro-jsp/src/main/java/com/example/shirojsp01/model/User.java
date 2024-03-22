package com.example.shirojsp01.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hanbing
 * @date 2024-03-21
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String salt;
}
