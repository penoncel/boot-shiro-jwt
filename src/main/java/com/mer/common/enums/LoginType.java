package com.mer.common.enums;

/**
 * 接口登入类型 枚举类
 * @author zhaoqi
 * @date 2020/5/20 17:20
 */
public enum LoginType {
    /**
     * 密码登录
     */
    PASSWORD_LOGIN_TYPE("PassWord"),
    /**
     * 验证码登录
     */
    CODE_LOGIN_TYPE("Code");

    private String type;

    LoginType(String type){
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

}
