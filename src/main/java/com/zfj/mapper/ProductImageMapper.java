package com.zfj.mapper;

import com.zfj.pojo.ProductImage;
import com.zfj.pojo.ProductImageExample;
import java.util.List;

public interface ProductImageMapper {

    int insert(ProductImage record);

    List<ProductImage> selectByExample(Integer product_id);

    int deleteByPrimaryKey(Integer id);

    ProductImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ProductImage record);

    int deleteByProductId(Integer product_id);


    int insertSelective(ProductImage record);

    int updateByPrimaryKeySelective(ProductImage record);
}