package com.imooc.youtube.service;

import com.imooc.youtube.dao.UserRoleDao;
import com.imooc.youtube.domain.auth.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    public List<UserRole> getUserRoleByUserId(Long userId) {
            return userRoleDao.getUserRoleByUserId(userId);
        }

    public void addUserRole(UserRole userRole) {
        userRole.setCreateTime(new Date());
        userRoleDao.addUserRole(userRole);
    }
}
