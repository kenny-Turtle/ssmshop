package com.zfj.service;

import com.zfj.mapper.PropertyMapper;
import com.zfj.mapper.PropertyValueMapper;
import com.zfj.pojo.Property;
import com.zfj.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/26 14:36
 */
@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Autowired
    private PropertyValueMapper propertyValueMapper;
    @Autowired
    private PropertyService propertyService;

    public void add(PropertyValue propertyValue) {
        propertyValueMapper.insert(propertyValue);
    }

    public void delete(Integer id) {
        propertyValueMapper.deleteByPrimaryKey(id);
    }

    public void deleteByProductId(Integer product_id) {
        propertyValueMapper.deleteByProductId(product_id);
    }

    public void update(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKey(propertyValue);
    }

    //根据产品id和分类id查询列表
    public List<PropertyValue> list(Integer product_id, Integer category_id) {
        //现根据分类id查询出属性id
        List<Property> properties = propertyService.list(category_id);
        //再根据产品id和属性id查询出列表
        for (Property property : properties) {
        propertyValueMapper.selectByExample(product_id,property.getId());
        }
        return null;
    }

    public PropertyValue get(Integer id) {
        return propertyValueMapper.selectByPrimaryKey(id);
    }

    public List<PropertyValue> listByProductId(Integer product_id) {
        return propertyValueMapper.selectByProductId(product_id);
    }
}
