package com.zfj.mapper;

import com.zfj.pojo.Category;
import com.zfj.pojo.CategoryExample;
import java.util.List;

public interface CategoryMapper {

    //根据id删除分类
    int deleteByPrimaryKey(Integer id);

    //添加一个分类
    int insert(Category record);


//    int insertSelective(Category record);

    //获取列表
    List<Category> selectByExample();

    //根据id获取分类
    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    //根据id更新分类
    int updateByPrimaryKey(Category record);
}