package com.zfj.service;

import com.zfj.mapper.OrderMapper;
import com.zfj.mapper.ProductMapper;
import com.zfj.pojo.Order;
import com.zfj.pojo.OrderItem;
import com.zfj.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/27 14:58
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ProductMapper productMapper;


    public int getIdByCode(String code) {
        return orderMapper.selectByCode(code);
    }

    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    public List<Order> listAll() {
        return orderMapper.selectByExample();
    }

    //按照用户和订单状态来查询
    public List<Order> list(Integer user_id, String excludedStatus) {

        return orderMapper.list(user_id,excludedStatus);
    }

    public List<Order> listByUserId(Integer user_id) {
        return orderMapper.selectByUserId(user_id);
    }

    public void update(Order order) {
        orderMapper.updateByPrimaryKey(order);
    }

    public void add(Order order) {
        orderMapper.insert(order);
    }

    //增加订单，返回一个float类型的数值用来表示该订单的总价
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public float add(Order order, List<OrderItem> orderItems) {

        float total = 0;
        add(order);

        System.out.println(">>>>>>>>>OrderServiceImpl<<<order_id:"+order.getId());
        System.out.println(">>>>>>>>>OrderServiceImpl<<<订单码:"+order.getOrder_code());
        System.out.println(">>>>>>>>>OrderServiceImpl<<<地址:"+order.getAddress());

//        int oid = orderMapper.selectByCode(order.getOrder_code());
//        System.out.println(">>>>>能不能获取到orderid《《《《"+oid);



        // 用来模拟当增加订单后出现异常，观察事务管理是否预期发生
        if (false)
            throw new RuntimeException();
/*
        for (OrderItem oi : orderItems) {
            System.out.println(">>>>>>>>>>是否进入方法《《《《");
            oi.setOrder_id(order.getId());
            System.out.println(">>>>>>>第二步《《《《"+oi.getUser_id()+"<<<<<");
            System.out.println(">>>>>>>《《《《"+oi.getProduct_id()+"<<<<<");
            System.out.println(">>>>>>>《《《《"+oi.getNumber()+"<<<<<");
            orderItemService.update(oi);
            System.out.println(">>>>>>>第三步《《《《");
            total += oi.getProduct().getPrice() * oi.getNumber();
            System.out.println(">>>>>>获取订单的总价："+total);
        }*/
        int i = orderMapper.selectByCode(order.getOrder_code());

        for (OrderItem oi : orderItems) {
            oi.setOrder_id(order.getId());
            Integer id = oi.getProduct_id();
            Product p = productMapper.selectByPrimaryKey(id);
            oi.setOrder_id(i);
            orderItemService.update(oi);
            total += p.getPrice() * oi.getNumber();
        }
        return total;
    }
}
