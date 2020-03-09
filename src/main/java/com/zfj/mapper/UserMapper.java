package com.zfj.mapper;

import com.zfj.pojo.User;
import com.zfj.pojo.UserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface UserMapper {

    //据id删除
    int deleteByPrimaryKey(Integer id);

    //添加
    int insert(User record);

    //获取所有
    List<User> selectByExample();

    //据id获取
    User selectByPrimaryKey(Integer id);

    //修改密码
    int updateByPrimaryKey(User record);

    //据姓名查找是否存在
    User isExist(String name);

    //据姓名查找用户
    User selectByName(String name);

    //根据用户名和密码查找
    User selectByNamePwd(@Param("name")String name,
                         @Param("password")String password);
}