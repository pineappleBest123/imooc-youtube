package com.imooc.youtube.service;

import com.imooc.youtube.dao.AuthRoleMenuDao;
import com.imooc.youtube.domain.auth.AuthRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class AuthRoleMenuService {

    @Autowired
    private AuthRoleMenuDao authRoleMenuDao;
    public List<AuthRoleMenu> getAuthRoleMenusByRoleIds(Set<Long> roleIdSet) {
        return authRoleMenuDao.getAuthRoleMenusByRoleIds(roleIdSet);
    }
}
