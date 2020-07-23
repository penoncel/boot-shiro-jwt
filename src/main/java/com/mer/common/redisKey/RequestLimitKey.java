package com.mer.common.redisKey;

import com.mer.common.constant.Constant;
import com.mer.framework.config.Redis.KeySet.BasePrefix;

/**
 * 重复提交 key
 */
public class RequestLimitKey extends BasePrefix {

    private RequestLimitKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    /**
     * 用户信息 token
     */
    public static RequestLimitKey requestLimit = new RequestLimitKey(Constant.repfat_timeOut,"requestLimit:");


}
