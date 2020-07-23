package com.mer.framework.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.mer.common.constant.Constant;
import com.mer.common.enums.ErrorState;
import com.mer.common.redisKey.LogingUserKey;
import com.mer.common.utils.JwtUtil;
import com.mer.common.utils.ServletUtils;
import com.mer.framework.Utils.SpringContextUtils;
import com.mer.framework.config.Redis.RedisService.RedisService;
import com.mer.framework.shiro.token.JwtToken;
import com.mer.framework.web.domain.LoginToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWt 过滤器
 * @author zhaoqi
 * @date 2020/5/20 17:20
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter{

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    /**
     * 执行登录认证
     * @param request ServletRequest
     * @param response ServletResponse
     * @param mappedValue mappedValue
     * @return 是否成功
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String token = ((HttpServletRequest) request).getHeader(Constant.TOKEN_HEADER_NAME);
        if (token != null) {
            return executeLogin(request, response);
        }else {
            // 没有携带Token
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            String httpMethod = httpRequest.getMethod();
            String requestURI = httpRequest.getRequestURI();
            logger.info("当前请求 {} Authorization属性(Token)为空 请求类型 {}", requestURI, httpMethod);
            JSONObject o = new JSONObject();
            o.put("code", ErrorState.TOKEN_ISNULL.getCode());
            o.put("msg", ErrorState.TOKEN_ISNULL.getMsg());
            ServletUtils.renderString((HttpServletResponse) response, o.toString());
            return false;
        }
        // 如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
//        return true;
    }


    /**
     * 执行登录
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response){
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String token = httpServletRequest.getHeader(Constant.TOKEN_HEADER_NAME);
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        try {
            getSubject(request, response).login(jwtToken);
            //获得手机号
            String phone = JwtUtil.getPhone(token);

            //获取 RedisServiceBean
            RedisService redisCache = SpringContextUtils.getBean("redisService", RedisService.class);
            //获取缓存的token对象
            LoginToken loginToken = redisCache.get(LogingUserKey.userToken,phone,LoginToken.class);
            if(null != loginToken){
                //是否需要刷星token
                boolean refresh =  JwtUtil.tokenTimeIsNotRefsh(loginToken.getRefresh_time());
                if(refresh){
                    LoginToken loginToken1 = JwtUtil.createToken(phone);
                    httpServletResponse.setHeader(Constant.TOKEN_HEADER_NAME,JSONObject.toJSON(loginToken1).toString());
                    redisCache.set(LogingUserKey.userToken,phone,loginToken1);
                }
            }

        }catch (AuthenticationException e){
            ServletUtils.renderString(httpServletResponse, e.getMessage());
            return false;
        }
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }


}
