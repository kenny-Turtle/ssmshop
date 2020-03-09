package com.zfj.controller;

import com.zfj.mapper.CategoryMapper;
import com.zfj.pojo.Category;
import com.zfj.pojo.Product;
import com.zfj.pojo.Review;
import com.zfj.pojo.Test;
import com.zfj.service.CategoryService;
import com.zfj.service.ProductService;
import com.zfj.service.ReviewService;
import com.zfj.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * @Author zfj
 * @create 2020/2/23 19:04
 */
@Controller
//@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    ProductService productService;

    @RequestMapping("/test")
    public String test(Model model){

        System.out.println("debug here");

        int id=2;
        Test aa = testService.findTestById(id);

        List<Test> all = testService.findAll();

        model.addAttribute("test",aa);
        model.addAttribute("all",all);

        return "test";
//        return "ok";
    }
    @RequestMapping("/test02")
    public String test02(Model model){
        List<Category> list = categoryService.list();
        Category category = categoryService.get(3);
        model.addAttribute("list",list);
        model.addAttribute("category",category);
        return "test";
    }
    @RequestMapping("/reviewTest")
    public String reviewTest(Model model) throws ParseException {

        return null;
    }
    @RequestMapping("/productTest")
    public String productTest(Model model){
        reviewService.delete(12);
        List<Product> products = productService.list(1);
        model.addAttribute("products",products);
        return "admin/test";
    }
}
