package com.zfj.controller;

import com.zfj.pojo.User;
import com.zfj.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/24 20:51
 */
@Api(description = "用户管理")
@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation("显示所有用户")
    @RequestMapping("/listUser")
    public String list(Model model) {
        List<User> users = userService.list();
        model.addAttribute("users", users);
        return "admin/listUser";
    }

    @ApiOperation("编辑用户")
    @RequestMapping("/editUser")
    public String edit(Model model, Integer id) {
        User user = userService.get(id);
        model.addAttribute("user", user);
        return "admin/editUser";
    }

    @ApiOperation("更新用户")
    @RequestMapping("/updateUser")
    public String update(Integer id, String password) {
        userService.updatePassword(id, password);
        return "redirect:listUser";
    }


    @ApiOperation("测试")
    @RequestMapping("/userTest")
    public String userTest(Model model){
        List<User> zfjs = userService.list();
        model.addAttribute("userTest",zfjs);

        boolean e = userService.isExist("小朱");
        model.addAttribute("test1",e);

        return "admin/test";
    }
}
