package com.zfj.controller;

import com.zfj.pojo.Product;
import com.zfj.pojo.ProductImage;
import com.zfj.service.ProductImageService;
import com.zfj.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/29 9:45
 */
@Api(description = "商品图片管理")
@Controller
@RequestMapping("/admin")
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;

    @ApiOperation("编辑商品图片")
    @RequestMapping("/editProductImage")
    public String edit(Model model,Integer product_id){

        List<ProductImage> productImages = productImageService.list(product_id);
        model.addAttribute("productImages",productImages);
        Product product = productService.get(product_id);
        model.addAttribute("product",product);
        return "admin/editProductImage";
    }

    @ApiOperation("更新商品图片")
    @RequestMapping(value = "/updateProductImage",method= RequestMethod.POST)
    public String update(HttpServletRequest request,
                         @RequestParam("productImage")ProductImage productImage,
                         Integer product_id,
                         Integer id,
                         @RequestParam("picture")MultipartFile picture){
        //上传文件到指定位置
        String filePath=request.getSession().getServletContext().
                getRealPath("img/product/"+product_id);
        // 因为 id 是自增长键，所以需要 % 5 来作为文件名
        String fileName = (id % 5 == 0 ? 5 : id % 5) + ".jpg";
        File uploadPicture = new File(filePath,fileName);
        if(!uploadPicture.exists()){
            uploadPicture.mkdirs();
        }
        //保存
        try {
            picture.transferTo(uploadPicture);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:editProductImage?product_id=" + product_id;

    }

    @ApiOperation("删除商品图片")
    @RequestMapping("/delectProductImage")
    public String delete(Integer id,Integer product_id,HttpServletRequest request){

        // 不删除表中的数据（在 ProductController 中统一删除），删除对应文件
        String filePath=request.getSession().
                getServletContext().getRealPath("img/product/"+product_id);
        String fileName=id+".jpg";
        new File(filePath,fileName).delete();

        return "redirect:editProductImage?product_id" + product_id;

    }








}
