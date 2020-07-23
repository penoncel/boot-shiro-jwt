package com.mer.common.constant;

import java.util.concurrent.TimeUnit;

/**
 * 常量类
 * @author zhaoqi
 * @date 2020/5/20 17:20
 */
public class Constant {


    /**
     * 验证码过期时间 此处为五分钟
     */
    public static Integer CODE_EXPIRE_TIME = 5;

    /**
     * jwtToken过期时间
     * 1000 * 60 * 60 =  一小时
     * 1000 * 60 * 60 * 24 一天
     */
    public static final long TOKEN_EXPIRE_TIME =  1000 * 60 * 60 * 24 * 2;

    /**
     * token redis 中过期时间
     * 60 -分钟
     * 60 * 60 一小时
     * 60 * 60 * 24 一天
     */
    public static int redis_token_timeOut = 60 * 60 * 24 * 2;


    /**
     * 1 秒内不允许重复提交
     * 1分钟内不得重复5次
     */
    public static int repfat_timeOut = 60 * 1;

    /**
     * token请求头名称
     */
    public static String TOKEN_HEADER_NAME = "X-Token";

    /**
     * 表单重复提交间隔时间 单位 S
     * 两次相同参数的请求，如果间隔时间大于该参数，系统不会认定为重复提交的数据
     */
    public static int FORM_REPEAT_TIME = 10;


    /***
     *  用户登入 信息过期时间
     *  public static final int TOKEN_EXPIRE = 3600*24 * 2;
     */
    public static final int loginUserMsg_timeOut = 10 * 6  * 60 *24 *3;// 60 秒 5分钟


    /***
     *  用户登入 信息过期时间
     *  public static final int TOKEN_EXPIRE = 3600*24 * 2;
     */
    public static final int smsCode_timeOut = 10 * 6 * 10;// 2分钟 秒


    /**
     * 盐
     */
    public static final String salt ="c46d05c7a12249cda67e20232ba2b459";




}
