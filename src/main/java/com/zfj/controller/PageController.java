package com.zfj.controller;

import com.zfj.pojo.OrderItem;
import com.zfj.service.OrderItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/29 15:03
 * 专门用于前台页面跳转的控制器
 */
@Api(description = "前台页面跳转")
@Controller
@RequestMapping("")
public class PageController {
    @Autowired
    OrderItemService orderItemService;


    //登录页面
    @ApiOperation("至登录页面")
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    //注册成功页面
    @ApiOperation("至注册成功页")
    @RequestMapping("/registerSuccessPage")
    public String registerSuccessPage() {
        return "registerSuccessPage";
    }

    //跳转至注册页面
    @ApiOperation("至注册页面")
    @RequestMapping("/registerPage")
    public String registerPage() {
        return "registerPage";
    }


    //购买页面
    @ApiOperation("测试，可继续使用")
    @RequestMapping("/test")
    public String testPage(Model model) {
        List<OrderItem> orderItems = orderItemService.getByOrderId(1);
        model.addAttribute("orderItems", orderItems);
        return "buyPage";
    }

    //付款页面
    @ApiOperation("至付款页")
    @RequestMapping("/payPage")
    public String payPage() {
        return "alipay";
    }


    @ApiOperation("至主页")
    @RequestMapping("/admin")
    public String admin() {
        return "admin/index";
    }

}
