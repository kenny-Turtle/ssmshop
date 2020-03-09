package com.zfj.service;

import com.zfj.mapper.UserMapper;
import com.zfj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/24 20:37
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> list() {
        return userMapper.selectByExample();
    }

    //更改用户密码
    public void updatePassword(int id, String password) {
        User user = userMapper.selectByPrimaryKey(id);
        user.setPassword(password);
        userMapper.updateByPrimaryKey(user);
    }

    public User get(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    //根据用户名和密码查询用户
    public User get(String name, String password) {
//        User user = userMapper.selectByName(name);
        User user = userMapper.selectByNamePwd(name, password);
        return user;
    }

    public boolean isExist(String name) {
        User exist = userMapper.isExist(name);
        boolean flag=(exist!=null);
        return (flag);
    }

    public void add(User user) {
        userMapper.insert(user);
    }
}
