package com.zfj.mapper;

import com.zfj.pojo.PropertyValue;
import com.zfj.pojo.PropertyValueExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PropertyValueMapper {

    int insert(PropertyValue record);

    int deleteByPrimaryKey(Integer id);

    //据产品id删除
    int deleteByProductId(Integer product_id);

    int updateByPrimaryKey(PropertyValue record);

    //
    List<PropertyValue> selectByExample(
            @Param("product_id")Integer product_id,
            @Param("property_id")Integer property_id);

    PropertyValue selectByPrimaryKey(Integer id);

    List<PropertyValue> selectByProductId(Integer product_id);

}