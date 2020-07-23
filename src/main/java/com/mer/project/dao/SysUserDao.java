package com.mer.project.dao;

import com.mer.project.pojo.SysUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SysUserDao {
    /**
     * 修改用户密码
     * @param sysUser
     * @return
     */
    @Update("update sys_user set password=#{password},salt=#{salt} where phone=#{phone}")
    boolean updatePassWorld(SysUser sysUser);

    /**
     * 添加用户信息
     * @param sysUser
     * @return
     */
    @Insert("insert into sys_user(phone,salt,password,icon,name,address,reg_times,sex,age) values(#{phone},#{salt},#{password},#{icon},#{name},#{address},#{reg_times},#{sex},#{age})")
    @Options(useGeneratedKeys=true, keyProperty = "id",keyColumn="id")
    int insertLoginUserMsg(SysUser sysUser);

    /**
     * 根据手机号查找用户
     */
    @Select("select * from sys_user where phone = #{phone}")
    SysUser findByUserPhone(@Param("phone") String phone);
}
