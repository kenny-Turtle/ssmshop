package com.zfj.controller;

import com.zfj.pojo.Product;
import com.zfj.pojo.Property;
import com.zfj.pojo.PropertyValue;
import com.zfj.service.ProductService;
import com.zfj.service.PropertyService;
import com.zfj.service.PropertyValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/29 9:08
 */
@Api(description = "属性值管理")
@Controller
@RequestMapping("/admin")
public class PropertyValueController {

    @Autowired
    PropertyValueService propertyValueService;

    @Autowired
    ProductService productService;

    @Autowired
    PropertyService propertyService;

    @ApiOperation("显示所有属性值")
    @RequestMapping("/listPropertyValue")
    public String list(Model model,Integer product_id,Integer category_id){
        //根据产品id返回属性值列表，并填充
        List<PropertyValue> propertyValues = propertyValueService.listByProductId(product_id);
        model.addAttribute("propertyValues",propertyValues);
        //据产品id返回产品
        Product product = productService.get(product_id);
        model.addAttribute("product",product);
        List<Property> list = propertyService.list(category_id);
        model.addAttribute("propertyies",list);
        return "admin/listPropertyValue";
    }

    @ApiOperation("增加属性值")
    @RequestMapping("/addPropertyValue")
    public String add(PropertyValue propertyValue){
        int product_id = propertyValue.getProduct_id();
        int category_id = productService.get(product_id).getCategory_id();
        propertyValueService.add(propertyValue);
        return "redirect:listPropertyValue?product_id=" + product_id + "&category_id=" + category_id;
    }

    @ApiOperation("删除属性值")
    @RequestMapping("/deletePropertyValue")
    public String delete(Integer id) {

        int product_id = propertyValueService.get(id).getProduct_id();
        int category_id = productService.get(product_id).getCategory_id();
        propertyValueService.delete(id);
        return "redirect:listPropertyValue?product_id=" + product_id + "&category_id=" + category_id;

    }

    @ApiOperation("编辑属性值")
    @RequestMapping("/editPropertyValue")
    public String edit(Integer id, Model model){
        PropertyValue propertyValue = propertyValueService.get(id);
        model.addAttribute("propertyValue",propertyValue);
        Product product = productService.get(propertyValue.getProduct_id());
        model.addAttribute("product",product);
        return "admin/editPropertyValue";
    }

    @ApiOperation("更新属性值")
    @RequestMapping("/updatePropertyValue")
    public String update(PropertyValue propertyValue){
        Integer product_id = propertyValue.getProduct_id();
        Integer category_id = productService.get(product_id).getCategory_id();
        propertyValueService.update(propertyValue);
        return "redirect:listPropertyValue?product_id"+product_id+"&category_id="+category_id;
    }

}
