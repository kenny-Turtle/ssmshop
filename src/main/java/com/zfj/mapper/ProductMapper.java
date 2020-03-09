package com.zfj.mapper;

import com.zfj.pojo.Product;
import com.zfj.pojo.ProductExample;
import java.util.List;

public interface ProductMapper {

    //增加产品
    int insert(Product record);

    //据id删除
    int deleteByPrimaryKey(Integer id);

    //更新产品
    int updateByPrimaryKey(Product record);

    //据id查找
    Product selectByPrimaryKey(Integer id);

    //返回所有
    List<Product> selectByExample();

    //根据分类id获取产品
    List<Product> selectByCategoryId(Integer category_id);

    int insertSelective(Product record);

    int updateByPrimaryKeySelective(Product record);
}