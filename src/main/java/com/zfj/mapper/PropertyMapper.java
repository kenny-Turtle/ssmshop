package com.zfj.mapper;

import com.zfj.pojo.Property;
import java.util.List;

public interface PropertyMapper {

    int insert(Property record);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(Property record);

    Property selectByPrimaryKey(Integer id);

    List<Property> selectByExample(Integer category_id);

}