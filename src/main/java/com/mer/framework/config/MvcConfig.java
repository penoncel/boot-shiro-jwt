package com.mer.framework.config;

import com.mer.framework.web.interceptor.RequestLimitIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private RequestLimitIntercept requestLimitIntercept;

    /**
     * 设置跨域访问
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                //可以被跨域的路径，”/**”表示无限制。
                .addMapping("/**")
                //”*”允许所有的请求域名访问跨域资源，当设置具体URL时只有被设置的url可以跨域访问。例如：allowedOrigins(“https://www.baidu.com”),则只有https://www.baidu.com能访问跨域资源。
                .allowedOrigins("*")
                //允许跨域访问资源服务器的请求方式，如：POST、GET、PUT、DELETE等，“*”表示无限制。
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                //是否允许用户发送、处理 cookie
                .allowCredentials(true)
                //预检请求的有效期，单位为秒。有效期内，不会重复发送预检请求
                .maxAge(3600 * 24);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("添加拦截");
        registry.addInterceptor(requestLimitIntercept).addPathPatterns("/**").excludePathPatterns();
    }


//    /**
//     * 添加jwt拦截器
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor())
//                // 拦截所有请求，通过判断 @JwtToken注解 决定是否需要登录
//                .addPathPatterns("/**");
//    }




}
