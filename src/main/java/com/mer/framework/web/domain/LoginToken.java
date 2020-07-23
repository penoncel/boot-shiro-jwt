package com.mer.framework.web.domain;

import lombok.Data;

@Data
public class LoginToken {

    /**
     * token
     */
    private String token;

    /**
     * 刷新时间（过期时间，在未到点之前进行刷新token）
     */
    private String refresh_time;


}
