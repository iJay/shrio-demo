package org.example.www.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Map;

/**
 * @author hanbing
 * @date 2024-03-20
 */
public class ShiroMd5Utils {
    public static void main(String[] args) {
        // 使用用户名作为盐 为了让盐是动态的
        String userName = "jack";

        // 使用md5
        Md5Hash md5Hash = new Md5Hash("123");
        // 202cb962ac59075b964b07152d234b70
        System.out.println(md5Hash.toHex());

        // 使用md5 + 盐
        Md5Hash md5Hash1 = new Md5Hash("123", userName);
        // 1d6c1e168e362bc0092f247399003a88
        System.out.println(md5Hash1.toHex());

        // 使用md5 + 盐 + hash散列
        Md5Hash md5Hash2 = new Md5Hash("123", userName, 1024);
        // 42df4a630b4b9915e8bcc0ef2d58c0eb
        System.out.println(md5Hash2.toHex());
    }
}
