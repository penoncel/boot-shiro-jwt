package com.mer.framework.web.exception;


import com.aliyuncs.exceptions.ClientException;

import com.mer.common.enums.ErrorState;
import com.mer.framework.web.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


/**
 * 全局异常处理
 * @author zhaoqi
 * @date 2020/5/20 17:20
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    /**
     * serviceException
     * @param e e
     * @return Result
     */
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public Result handleServiceException(ServiceException e) {
//        return ResponseEntity.status(e.getCode()).body(e.getMessage());
        return Result.error(e.getCode(),e.getMessage());
    }


    /**
     * 参数校验异常
     * @param e e
     * @return Result
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        return Result.error(400,e.getMessage());
    }


    /**
     * 阿里短信发送异常
     * @return Result
     */
    @ResponseBody
    @ExceptionHandler(ClientException.class)
    public Result handleClientException(){
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorState.SEND_SMS_ERROR.getMsg());
        return Result.error(ErrorState.SEND_SMS_ERROR.getCode(),ErrorState.SEND_SMS_ERROR.getMsg());
    }


    /**
     * shiro权限异常处理
     * @return Result
     */
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public Result handleShiroException() {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorState.NOT_AUTH.getMsg());
        return Result.error(ErrorState.NOT_AUTH.getCode(),ErrorState.NOT_AUTH.getMsg());
    }


    /**
     * token无效异常
     */
    @ResponseBody
    @ExceptionHandler(IncorrectCredentialsException.class)
    public Result handleTokenException(){
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorState.TOKEN_INVALID.getMsg());
        return Result.error(ErrorState.TOKEN_INVALID.getCode(),ErrorState.TOKEN_INVALID.getMsg());
    }


    /**
     * 参数校验(缺少)异常处理
     * @return Result
     */
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingParameterException(){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorState.MISSING_PARAMETER.getMsg());
        return Result.error(ErrorState.MISSING_PARAMETER.getCode(),ErrorState.MISSING_PARAMETER.getMsg());
    }



    /**
     * SYSTEM_ERROR
     * @return Result
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e){
        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorState.MISSING_PARAMETER.getMsg());
        return Result.error(ErrorState.SYSTEM_ERROR.getCode(),ErrorState.SYSTEM_ERROR.getMsg());
    }

}
