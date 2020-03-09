package com.zfj.service;

import com.zfj.mapper.OrderItemMapper;
import com.zfj.pojo.Order;
import com.zfj.pojo.OrderItem;
import com.zfj.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/28 9:20
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;

    public OrderItem getById(Integer id) {
        return orderItemMapper.selectByPrimaryKey(id);
    }

    public void add(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    public List<OrderItem> getByOrderId(Integer order_id) {
        return orderItemMapper.selectByOrderId(order_id);
    }

    public void update(OrderItem orderItem) {
        orderItemMapper.updateByPrimaryKey(orderItem);
    }

    public List<OrderItem> listByUserId(Integer user_id) {
        return orderItemMapper.listByUserId(user_id);
    }

    //返回当前user_id下的购物车订单列表
    public List<OrderItem> listForCart(Integer user_id) {
        List<OrderItem> result = orderItemMapper.listForCart(user_id);
        setProduct(result);

        return result;
    }

    public void delete(Integer id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }


    private void setProduct(OrderItem oi) {
        Product p = productService.get(oi.getProduct_id());
        oi.setProduct(p);
        //debug here
//        System.out.println(">>>>+++++++<<<<"+p.getPrice()+"<<<<<<<<");
    }

    public void setProduct(List<OrderItem> ois){
        for (OrderItem orderItem : ois) {
            setProduct(orderItem);
        }
    }

    //为List<Order>填充订单项
    public void fill(List<Order> orders) {
        for (Order order : orders) {
            fill(order);
        }
    }

    //为Order填充订单项
    public void fill(Order o) {

        List<OrderItem> orderItems = orderItemMapper.selectByOrderId(o.getId());

        setProduct(orderItems);

        float total = 0;//总价
        int totalNumber = 0;//总数
        for (OrderItem or : orderItems) {
            total += or.getNumber()*or.getProduct().getPrice();
            totalNumber=or.getNumber();
        }

        //用item项算出总数和总价之后绑定到order类里，为order填充订单项
        o.setTotal(total);
        o.setTotalNumber(totalNumber);
        o.setOrderItems(orderItems);

    }
}
