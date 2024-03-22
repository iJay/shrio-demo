package com.example.shirojsp01.utils;

import java.util.Random;

/**
 * @author hanbing
 * @date 2024-03-21
 */
public class SaltUtils {
    /**
     * 生成salt的静态方法
     *
     * @param n 随机数的长度
     * @return String
     */
    public static String getSalt(int n) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }
}
