package com.zfj.service;

import com.zfj.mapper.PropertyMapper;
import com.zfj.pojo.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/27 14:15
 */
@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyMapper propertyMapper;

    public void add(Property property) {
        propertyMapper.insert(property);
    }

    public void delete(Integer id) {
        propertyMapper.deleteByPrimaryKey(id);
    }

    public void update(Property property) {
        propertyMapper.updateByPrimaryKey(property);
    }

    public List<Property> list(Integer category_id) {
        return propertyMapper.selectByExample(category_id);
    }

    public Property get(Integer id) {
        return propertyMapper.selectByPrimaryKey(id);
    }
}
