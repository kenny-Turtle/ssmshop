package com.zfj.mapper;

import com.zfj.pojo.Review;
import com.zfj.pojo.ReviewExample;
import java.util.List;

public interface ReviewMapper {

    //获取所有
    List<Review> selectByExample();

    //据id获取数据
    Review selectByPrimaryKey(Integer id);

    //据id删除数据
    int deleteByPrimaryKey(Integer id);

    //增加一条评论
    int insert(Review record);

    //更新评论
    int updateByPrimaryKey(Review record);

    //根据产品id获取所有评论
    List<Review> selectByProductId(Integer product_id);

    int insertSelective(Review record);

    int updateByPrimaryKeySelective(Review record);
}