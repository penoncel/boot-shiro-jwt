package com.mer.project.Vo;

import lombok.Data;

/***
 * 返回模板
 */
@Data
public class LogingUserVo {

    /**手机号*/
    private String phone;

    /**姓名*/
    private String nickname;

    /**头像*/
    private String icon;

    /**性别*/
    private int sex;

    /**年龄*/
    private int age;

    /**地址*/
    private String address;

}
