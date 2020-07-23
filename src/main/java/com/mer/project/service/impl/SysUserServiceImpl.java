package com.mer.project.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.mer.common.enums.ErrorState;
import com.mer.common.enums.LoginType;
import com.mer.common.enums.RoleEnums;
import com.mer.common.redisKey.LogingUserKey;
import com.mer.common.utils.CommonsUtils;
import com.mer.common.utils.JwtUtil;
import com.mer.common.utils.WriteFrom;
import com.mer.framework.config.Redis.RedisService.RedisService;
import com.mer.framework.shiro.token.CustomizedToken;
import com.mer.framework.web.domain.LoginToken;
import com.mer.framework.web.domain.LoginUser;
import com.mer.framework.web.domain.Result;
import com.mer.project.Vo.LogingUserVo;
import com.mer.project.dao.SysUserDao;
import com.mer.project.pojo.SysUser;
import com.mer.project.service.SysPermissionsServer;
import com.mer.project.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserDao userDao;


    @Autowired
    private RedisService redisService;

    @Autowired
    private SysPermissionsServer permissionsServer;



    @Override
    public Result modifyPassword(String phone, String code, String password) {

        // 判断redis中是否存在验证码
        String modifyCode = redisService.getString(LogingUserKey.modifyPassWordCode,phone);
        if(Objects.isNull(modifyCode)){
            return Result.error(ErrorState.CODE_EXPIRE);
        }

        // 判断redis中code与传递过来的code 是否相等
        if(!Objects.equals(CommonsUtils.encryptPassword(code), modifyCode)){
            return Result.error(ErrorState.CODE_ERROR);
        }

        // 如果用户不存在，执行注册
        SysUser user = this.findByUserPhone(phone);
        if(Objects.isNull(user)){
            try{
                Boolean flag = this.registerLoginUser(phone, password);
                if(flag){
                    return Result.success(this.returnLoginInitParam(phone));
                }else {
                    return Result.error();
                }
            }catch (Exception e){
                logger.error(WriteFrom.WriterEx(e));
                redisService.delete(LogingUserKey.loginUserIinfo,phone);
                throw e;
            }
        }

        user.setPhone(phone);
        user.setSalt(CommonsUtils.uuid());
        user.setPassword(CommonsUtils.encryptPassword(password));

        // 删除缓存用户信息
        redisService.delete(LogingUserKey.loginUserIinfo,phone);
        boolean flag = userDao.updatePassWorld(user);
        if(flag){
            // 修改完后重新设置用户缓存信息
            redisService.set(LogingUserKey.loginUserIinfo,phone,user);
            return Result.success(this.returnLoginInitParam(phone));
        }else {
            return Result.error();
        }

    }



    @Override
    public Boolean registerLoginUser(String phone, String... args) {
        SysUser user = new SysUser();
        user.setPhone(phone);
        // 如果有密码，则使用用户输入的密码
        String encryptPassword;
        if(args.length > 0){
            encryptPassword = CommonsUtils.encryptPassword(args[0]);
        }else{
            // 默认手机号后6未作为密码
            encryptPassword = CommonsUtils.encryptPassword(phone.substring(5, 11));
        }
        user.setSalt(CommonsUtils.uuid());
        user.setPassword(encryptPassword);
        user.setIcon("/test.jpg");
        user.setName(null);
        user.setAddress(null);

        // 用户注册
        userDao.insertLoginUserMsg(user);
        // 权限配置
        permissionsServer.addRole(user.getId(), RoleEnums.ADMIN.getCode(), RoleEnums.COMMON.getCode());
        //缓存LoginUser对象
        redisService.setLogingUserRedis(user);
        return Boolean.TRUE;

    }

    @Override
    public Result loginByCode(String phone, String code) {
//        logger.info(String.format(" 手机号 [%s] 验证码登入",phone));
        // 验证验证码是否存在redis
        String smsCode =  redisService.getString(LogingUserKey.smsCode,phone);
        if (smsCode == null) {
            return Result.error(ErrorState.CODE_EXPIRE);
        }

        // 验证码验证
        String redis_code = CommonsUtils.encryptPassword(code);
        if(!Objects.equals(redis_code, smsCode)){
            return Result.error(ErrorState.CODE_ERROR);
        }

        try{
            // 检查redis是否存在用户信息
            if(Boolean.FALSE == redisService.exists(LogingUserKey.loginUserIinfo,phone)){
                // 数据库中是否存在
                SysUser user =this.findByUserPhone(phone);
                //数据库中也没有，就自动 注册一下 然后设置到缓存
                if(Objects.isNull(user)){
                    try{
                        this.registerLoginUser(phone);
                    }catch (Exception e){
                        logger.error(WriteFrom.WriterEx(e));
                        // 由于redis没有事务回滚，需要在这里进行手动删除
                        redisService.delete(LogingUserKey.loginUserIinfo,phone);
                        throw e;
                    }
                }else {
                    //数据库存在则进行缓存
                    redisService.setLogingUserRedis(user);
                }
            }
            // 获取Subject
            Subject subject = SecurityUtils.getSubject();
            // 封装用户数据
            CustomizedToken token = new CustomizedToken(phone, code, LoginType.CODE_LOGIN_TYPE.toString());
            // 执行登录方法
            subject.login(token);
            // 返回登录后初始化参数
            return Result.success(returnLoginInitParam(phone));
        }catch (UnknownAccountException e) {
            return Result.error(ErrorState.USERNAME_NOT_EXIST);
        }catch (ExpiredCredentialsException e){
            return Result.error(ErrorState.CODE_EXPIRE);
        } catch (IncorrectCredentialsException e){
            return Result.error(ErrorState.CODE_ERROR);
        }

    }

    @Override
    public Result loginByPassword(String phone, String password) {
//        logger.info(String.format(" 手机号 [%s] 密码登入",phone));
        try{
            // 获取Subject
            Subject subject = SecurityUtils.getSubject();
            // 封装用户数据
            CustomizedToken token = new CustomizedToken(phone, password, LoginType.PASSWORD_LOGIN_TYPE.toString());
            // 执行登录方法
            subject.login(token);
            // 返回登录后初始化参数
            return Result.success(returnLoginInitParam(phone));
        }catch (UnknownAccountException e) {
            return Result.error(ErrorState.USERNAME_NOT_EXIST);
        } catch (IncorrectCredentialsException e){
            return Result.error(ErrorState.PASSWORD_ERROR);
        }
    }

    /**
     * 从redis 中获取 信息 返回登录后初始化参数
     * @param phone phone
     * @return Map<String, Object>
     */
    private Map<String, Object> returnLoginInitParam(String phone) {

        // 按钮权限，角色权限，角色信息
        LoginUser loginUser = redisService.get(LogingUserKey.loginUserIinfo,phone, LoginUser.class);
        if(Objects.isNull(loginUser)){
            loginUser = redisService.setLogingUserRedis(this.findByUserPhone(phone));
        }

        // 设置登入返回的模型
        LogingUserVo logingUserVo = new LogingUserVo();
        logingUserVo.setPhone(loginUser.getUser().getPhone());
        logingUserVo.setNickname(loginUser.getUser().getName());
        logingUserVo.setAge(loginUser.getUser().getAge());
        logingUserVo.setSex(loginUser.getUser().getSex());
        logingUserVo.setIcon(loginUser.getUser().getIcon());
        logingUserVo.setAddress(loginUser.getUser().getAddress());

        //登入成功时，刷新旧的token。
        if(redisService.exists(LogingUserKey.userToken,phone)){
            redisService.delete(LogingUserKey.userToken,phone);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("userinfo", logingUserVo);
        //生成新的token信息
        LoginToken loginToken = JwtUtil.createToken(phone);
        data.put("token", loginToken);
        redisService.set(LogingUserKey.userToken,phone,loginToken);
        return data;
    }

    @Override
    public SysUser findByUserPhone(String phone) {
        return userDao.findByUserPhone(phone);
    }

    @Override
    public void sendModifyPasswordCode(String phone) throws ClientException {
        // 这里使用默认值，随机验证码的方法为CommonsUtils.getCode()
        int code = 7777;
        // todo 此处为发送验证码代码

        // 将验证码加密后存储到redis中
        redisService.set(LogingUserKey.modifyPassWordCode,phone,CommonsUtils.encryptPassword(String.valueOf(code)));
    }

    @Override
    public void sendLoginCode(String phone) throws ClientException {
        // 这里使用默认值，随机验证码的方法为CommonsUtils.getCode()
        int code = 6666;
        // todo 此处为发送验证码代码

        // 将验证码加密后存储到redis中
        redisService.set(LogingUserKey.smsCode,phone,CommonsUtils.encryptPassword(String.valueOf(code)));
    }

}
