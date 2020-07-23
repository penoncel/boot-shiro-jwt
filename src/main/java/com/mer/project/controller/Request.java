package com.mer.project.controller;

import com.mer.framework.annotction.RequestLimit;
import com.mer.framework.web.controller.BaseController;
import com.mer.framework.web.domain.Result;
import com.mer.project.pojo.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Api(tags = "【user】用户")
@RestController
@RequestMapping("/apitest")
@Validated
public class Request extends BaseController {

    @RequestLimit(second = 60,maxCount = 5)
    @RequestMapping("/get")
    public Object get(){

        return "get";
    }

}
