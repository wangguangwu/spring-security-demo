package com.wangguangwu.dao;

import com.wangguangwu.entity.Role;
import com.wangguangwu.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    //提供根据用户名返回用户方法
    User loadUserByUsername(String username);

    //提供根据用户id查询用户角色信息方法
    List<Role> getRolesByUid(Integer uid);

}
