package com.zfj.controller;

import com.zfj.mapper.OrderMapper;
import com.zfj.pojo.Order;
import com.zfj.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/28 15:20
 */
@Api(description = "订单管理")
@Controller
@RequestMapping("/admin")
public class OrderController {

    @Autowired
    OrderService orderService;

    @ApiOperation("显示所有订单")
    @RequestMapping("/listOrder")
    public String listOrder(Model model){
        List<Order> orders = orderService.listAll();
        model.addAttribute("orders",orders);
        return "admin/listOrder";
    }

    @ApiOperation("更新订单")
    @RequestMapping("/updateOrder")
    public String update(Order order){
        orderService.update(order);
        return "redirect:listOrder";
    }

    @ApiOperation("更改订单状态：交货")
    @RequestMapping("orderDelivery")//交货
    public String delivery(Integer order_id) {
        Order order = orderService.get(order_id);
        order.setDelivery_date(new Date());
        order.setStatus(OrderService.waitConfirm);
        orderService.update(order);
        return "redirect:listOrder";
    }

}
