package com.zfj.mapper;

import com.zfj.pojo.OrderItem;
import com.zfj.pojo.OrderItemExample;
import java.util.List;

public interface OrderItemMapper {
    int insert(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int deleteByPrimaryKey(Integer id);

    List<OrderItem> selectByExample();

    List<OrderItem> selectByOrderId(Integer order_id);

    int updateByPrimaryKey(OrderItem record);

    List<OrderItem> listByUserId(Integer user_id);

    //user_id 但 order_id为空的数据
    List<OrderItem> listForCart(Integer user_id);

    int insertSelective(OrderItem record);

    int updateByPrimaryKeySelective(OrderItem record);
}