package com.mer.project.service;


import java.util.Set;

public interface SysPermissionsServer {

    /**
     * 根据用户id获取其角色列表
     * @param userId userId
     * @return Set<Role>
     */
    Set<String> getPermissionsSet(Integer userId);

    /**
     * 根据用户id 获取其角色 id 列表
     * @param userId userId
     * @return Set<Integer>
     */
    Set<Integer> getRoleIdSet(Integer userId);

    /**
     * 根据用户id获取其角色key列表
     * @param userId userId
     * @return Set<Role>
     */
    Set<String> getRoleSet(Integer userId);

    /**
     * 添加用户角色权限
     * @param userId
     * @param roleIds
     */
    int addRole(Integer userId, Integer... roleIds);
}
