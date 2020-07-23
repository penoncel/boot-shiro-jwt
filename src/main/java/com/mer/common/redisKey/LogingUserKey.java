package com.mer.common.redisKey;

import com.mer.common.constant.Constant;
import com.mer.framework.config.Redis.KeySet.BasePrefix;

/**
 * 用户登入 key
 */
public class LogingUserKey extends BasePrefix {

    private LogingUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    /**
     * 用户信息 token
     */
    public static LogingUserKey userToken = new LogingUserKey(Constant.redis_token_timeOut,"userToken:");

    /**
     * 登入用户 信息
     */
    public static LogingUserKey loginUserIinfo = new LogingUserKey(Constant.loginUserMsg_timeOut,"loginUserIinfo:");

    /**
     * 登入 验证码 key
     */
    public static LogingUserKey smsCode = new LogingUserKey(Constant.smsCode_timeOut,"smsCode:");


    /**
     * 通过验证码 修改密码
     */
    public static LogingUserKey modifyPassWordCode = new LogingUserKey(Constant.smsCode_timeOut,"modifyPassWordCode:");

}
