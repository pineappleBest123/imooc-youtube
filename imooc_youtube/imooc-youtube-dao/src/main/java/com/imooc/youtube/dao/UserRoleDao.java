package com.imooc.youtube.dao;

import com.imooc.youtube.domain.auth.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserRoleDao {
    List<UserRole> getUserRoleByUserId(Long userId);

    void addUserRole(UserRole userRole);
}
