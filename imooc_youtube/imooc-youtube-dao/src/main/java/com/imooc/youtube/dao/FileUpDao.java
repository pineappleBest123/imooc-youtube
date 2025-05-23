package com.imooc.youtube.dao;

import com.imooc.youtube.domain.FileUp;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface FileUpDao {

    Integer addFile(FileUp file);

    FileUp getFileByMD5(String md5);
}
