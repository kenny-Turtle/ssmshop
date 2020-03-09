package com.zfj.mapper;

import com.zfj.pojo.Order;
import com.zfj.pojo.OrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    Order selectByPrimaryKey(Integer id);

    //根据code返回订单id
    int selectByCode(String code);

    List<Order> selectByExample();

    List<Order> selectByUserId(Integer id);

    int updateByPrimaryKey(Order record);

    int insert(Order record);

    int deleteByPrimaryKey(Integer id);

    List<Order> list(@Param("user_id") Integer user_id,
                     @Param("status") String excludedStatus);

    int insertSelective(Order record);

    int updateByPrimaryKeySelective(Order record);
}