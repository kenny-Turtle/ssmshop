package com.zfj.controller;

import com.zfj.pojo.Category;
import com.zfj.pojo.Property;
import com.zfj.service.CategoryService;
import com.zfj.service.ProductService;
import com.zfj.service.PropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/28 15:54
 */
@Api(description = "商品属性管理")
@Controller
@RequestMapping("/admin")
public class PropertyController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyService propertyService;


    @ApiOperation("显示所有属性")
    @RequestMapping("/listProperty")
    public String list(Model model,Integer category_id){
        List<Property> properties = propertyService.list(category_id);
        model.addAttribute("properties",properties);
        Category category = categoryService.get(category_id);
        model.addAttribute("category",category);
        return "admin/listProperty";
    }

    @ApiOperation("添加一个属性")
    @RequestMapping("/addProperty")
    public String add(Property property) {
        propertyService.add(property);
        return "redirect:listProperty?category_id=" + property.getCategory_id();
    }

    @ApiOperation("删除一个属性")
    @RequestMapping("/deleteProperty")
    public String delete(Integer id) {
        int category_id = propertyService.get(id).getCategory_id();
        propertyService.delete(id);
        return "redirect:listProperty?category_id=" + category_id;
    }

    @ApiOperation("编辑属性")
    @RequestMapping("/editProperty")
    public String edit(Integer id, Model model) {
        Property property = propertyService.get(id);
        model.addAttribute("property", property);
        Category category = categoryService.get(property.getCategory_id());
        model.addAttribute("category", category);
        return "admin/editProperty";
    }

    @ApiOperation("更新属性")
    @RequestMapping("/updateProperty")
    public String update(Property property) {
        propertyService.update(property);
        return "redirect:listProperty?category_id=" + property.getCategory_id();
    }







}
