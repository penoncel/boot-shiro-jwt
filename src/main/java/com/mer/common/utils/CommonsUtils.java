package com.mer.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.UUID;


/**
 * public utils
 * @author zhaoqi
 * @date 2020/5/20 17:20
 */
@Slf4j
public class CommonsUtils {

    /**
     * 获取六位数验证码
     * @return 验证码
     */
    public static int getCode(){
        return (int)((Math.random()*9+1)*1000);
    }


    /**
     * 使用SHA256加密
     * @param password 需要加密的密码
     * @return 返回加密后的密码
     */
    public static String encryptPassword(String password){
//        return String.valueOf(new Sha256Hash(password, salt, 1024));
        return String.valueOf(new SimpleHash("md5", password, null, 2));
    }


    /**
     * 获取uuid
     * @return string
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }


}
