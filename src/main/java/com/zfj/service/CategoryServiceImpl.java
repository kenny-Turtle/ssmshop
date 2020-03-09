package com.zfj.service;

import com.zfj.mapper.CategoryMapper;
import com.zfj.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/24 19:26
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> list() {

        List<Category> categories = categoryMapper.selectByExample();
        return categories;
    }

    //更具id获取分类
    public Category get(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    public void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }
}
