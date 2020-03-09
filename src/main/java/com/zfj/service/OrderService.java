package com.zfj.service;

import com.zfj.pojo.Order;
import com.zfj.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/27 14:55
 */
public interface OrderService {

    //订单状态
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";//等待交货
    String waitConfirm = "waitConfirm";//等待确认
    String waitReview = "waitReview";//等待评论
    String finish = "finish";
    String delete = "delete";

    /*
    * 根据code返回order_id
    * */
    int getIdByCode(String code);

    /**
     * 根据ID返回对应的Order
     *
     * @param id
     * @return
     */
    Order get(int id);

    /**
     * 返回所有的订单
     *
     * @return
     */
    List<Order> listAll();

    /**
     * 按照用户、订单状态来查询
     *
     * @param user_id
     * @param excludedStatus
     * @return
     */
    List<Order> list(Integer user_id,String excludedStatus);

    /**
     * 返回user_id下的所有订单
     *
     * @param user_id
     * @return
     */
    List<Order> listByUserId(Integer user_id);

    /**
     * 更新订单
     *
     * @param order
     */
    void update(Order order);

    /**
     * 增加订单
     *
     * @param order
     */
    void add(Order order);

    /**
     * 增加订单，返回一个float类型的数值用来表示该订单的总价
     *
     * @param order
     * @param orderItems
     * @return
     */
    float add(Order order, List<OrderItem> orderItems);
}
