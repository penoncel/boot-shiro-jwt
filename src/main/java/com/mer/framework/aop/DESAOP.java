package com.mer.framework.aop;

import com.alibaba.fastjson.JSON;
import com.mer.framework.Utils.AESUtils;
import com.mer.framework.annotction.DES;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: SecretAOPController
 * @description: 切面加密解密
 * @author: zq
 **/
@Aspect
@Component
public class DESAOP {
    private static final Logger logger = LoggerFactory.getLogger(DESAOP.class);

    // 是否进行加密解密，通过配置文件注入（不配置默认为true）
    @Value("${isSecret:true}")
    boolean isSecret;

    // 定义切点,使用了@Secret注解的类 或 使用了@Secret注解的方法
    @Pointcut("@within(com.mer.framework.annotction.DES) || @annotation(com.mer.framework.annotction.DES)")
    public void pointcut(){}

    // 环绕切面
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point){
        System.out.println("\r");
        try {
            // 接收到请求，记录请求内容
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            // 参数[%s] ,Arrays.toString(point.getArgs())
            logger.info(String.format("{DESAOP}请求地址[%s] 请求类型[%s] 实现方法[%s] ",request.getRequestURL().toString(),request.getMethod(),(point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName())    ));

            // 获取被代理对象
            Object target = point.getTarget();
            // 获取通知签名
            MethodSignature signature = (MethodSignature )point.getSignature();
            // 获取被代理方法
            Method pointMethod = target.getClass().getMethod(signature.getName(), signature.getParameterTypes());
            // 获取被代理方法上面的注解@Secret
            DES secret = pointMethod.getAnnotation(DES.class);
            // 被代理方法上没有，则说明@Secret注解在被代理类上
            if(secret==null){
                secret = target.getClass().getAnnotation(DES.class);
            }
            if(secret!=null){
                // 获取被代理方法参数
                Object[] args = point.getArgs();
                if (args == null || args.length == 0) {
                    Map map =new HashMap();
                    map.put("code",500001);
                    map.put("msg","参数不能为空");
                    String msg = JSON.toJSONString(map);
                    logger.error("{DESAOP}响应结果：["+msg+"]");
                    return msg;
                }
                // 获取注解上声明的加密参数名
                String signName = secret.signName();

                for (Object obj : args){
                    if(obj instanceof Map){
                        Map<String, String> param = (Map<String, String>) obj;
                        if (param != null && param.get(signName) != null) {
                            logger.info("{DESAOP}请求报文：["+param.toString()+"]");
                            logger.info("{DESAOP}待解密参数：["+param.get(secret.signName())+"]");
                            try{
                                // 解密
                                String str = AESUtils.decrypt(param.get(secret.signName()));
                                logger.info("{DESAOP}解密后参数：["+str+"]");
                                // 转换vo
                                obj = JSON.parseObject(str);
                                //移除掉原来的sing
                                param.remove(param.get(secret.signName()));
                                //设置新的sing
                                param.put(secret.signName(),obj.toString());
                                logger.info("{DESAOP}方法前参数：["+param.toString()+"]");
                            }catch (Exception e){
                                Map map =new HashMap();
                                map.put("code",500002);
                                map.put("msg","解密失败");
                                String msg = JSON.toJSONString(map);
                                logger.error("{DESAOP}响应结果：["+msg+"]");
                                return msg;
                            }
                        }else{
                            Map map =new HashMap();
                            map.put("code",500003);
                            map.put("msg",signName +"参数不能为空");
                            String msg = JSON.toJSONString(map);
                            logger.error("{DESAOP}响应结果：["+msg+"]");
                            return msg;
                        }
                    }else{
                        System.out.println("其他类型");
                    }
                }

                // 执行请求
                Object result =  point.proceed();
                // 判断配置是否需要返回加密
                if(isSecret){
                    Map<String,Object> respMap = JSON.parseObject(result.toString(),Map.class);
                    if(respMap.get("data")!="" && respMap.get("data")!=null){
                        respMap.put("data",AESUtils.encrypt(respMap.get("data").toString()));
                    }
                    String msg = JSON.toJSONString(respMap);
                    logger.info("{DESAOP}响应结果：["+msg+"]");
                    return msg;
                }
                return result;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Map map =new HashMap();
            map.put("code",500004);
            map.put("msg","@DES 注解指定的类没有字段:encryptStr,或encryptStrName参数字段不存在");
            String msg = JSON.toJSONString(map);
            logger.error("{DESAOP}响应结果：["+msg+"]");
            return msg;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }
}
