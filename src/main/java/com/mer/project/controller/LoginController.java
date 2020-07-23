package com.mer.project.controller;


import com.aliyuncs.exceptions.ClientException;
import com.mer.framework.annotction.PhoneNumber;

import com.mer.framework.annotction.RequestLimit;
import com.mer.framework.web.domain.Result;
import com.mer.project.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;


@Api(tags = "【user】登录")
@RequestMapping("/login")
@RestController
@Validated
@RequestLimit(maxCount = 5,second = 1)
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private SysUserService userService;


    /**
     * 密码登入
     * @param phone
     * @param password
     * @return
     */
    @ApiOperation("密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "string")
    })
    @PostMapping("/pwdLogin")
    public Result pwdLogin(@PhoneNumber String phone, @NotEmpty(message = "密码不能为空")String password){
        return userService.loginByPassword(phone,password);
    }


    /**
     * 修改密码
     * @param phone
     * @param code
     * @param password
     * @return
     */
    @ApiOperation("通过验证码修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "string")
    })
    @PutMapping("/modifyPassword")
    public Result modifyPassword(@PhoneNumber String phone,
                                 @NotEmpty(message = "验证码不能为空") String code,
                                 @NotEmpty(message = "密码不能为空") String password){
        return userService.modifyPassword(phone, code, password);
    }

    /**
     * 修改密码 - 发送修改的验证码
     * @param phone
     * @return
     * @throws ClientException
     */
    @ApiOperation("修改密码(发送验证码)")
    @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query")
    @GetMapping("/modifyPasswordCode")
    public Result sendModifyPasswordCode(@PhoneNumber String phone) throws ClientException {
        userService.sendModifyPasswordCode(phone);
        return Result.success();
    }

    /**
     * 验证码登入
     * @param phone
     * @param code
     * @return
     */
    @ApiOperation("验证码登录(获取登入验证码)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "string"),
    })
    @PostMapping("/codeLogin")
    public Result codeLogin(@PhoneNumber String phone, @NotEmpty(message = "验证码不能为空")String code){
        return userService.loginByCode(phone,code);
    }

    /**
     * 验证码登入-发送登入的验证码
     * @param phone
     * @return
     * @throws ClientException
     */
    @ApiOperation(value = "发送登录验证码")
    @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "query")
    @GetMapping("/sendLoginCode")
    public Result sendLoginCode(@PhoneNumber String phone) throws ClientException {
        userService.sendLoginCode(phone);
        return Result.success();
    }



//    @GetMapping(value = "/logout")
//    public ResponseEntity<Void> logout() {
//        Subject subject = SecurityUtils.getSubject();
//        if(subject.getPrincipals() != null) {
//            UserDto user = (UserDto)subject.getPrincipals().getPrimaryPrincipal();
//            userService.deleteLoginInfo(user.getUsername());
//        }
//        SecurityUtils.getSubject().logout();
//        return ResponseEntity.ok().build();
//    }
}
