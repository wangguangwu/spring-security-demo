package com.wangguangwu.springsecurity02.dao;

import com.wangguangwu.springsecurity02.entity.Role;
import com.wangguangwu.springsecurity02.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangguangwu
 */
@Mapper
public interface UserDao {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User loadUserByUsername(String username);

    /**
     * 根据用户 id 查询角色
     *
     * @param uid 用户id
     * @return 角色信息
     */
    List<Role> getRolesByUid(Integer uid);

}
