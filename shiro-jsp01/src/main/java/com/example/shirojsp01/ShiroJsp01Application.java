package com.example.shirojsp01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
@MapperScan("com.example.shirojsp01.mapper")
public class ShiroJsp01Application {

    public static void main(String[] args) {
        SpringApplication.run(ShiroJsp01Application.class, args);
    }

}
