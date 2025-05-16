package com.imooc.youtube.dao;

import com.imooc.youtube.domain.UserMoment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMomentsDao {
    Integer addUserMoments(UserMoment userMoment);
}
