package com.zfj.service;

import com.zfj.mapper.ProductImageMapper;
import com.zfj.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/26 14:14
 */
@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageMapper productImageMapper;

    public void add(ProductImage image) {
        productImageMapper.insert(image);
    }

    //根据产品id删除
    public void deleteByProductId(Integer product_id) {
        /*//查询出需要删除的列表
        List<ProductImage> productImages = list(product_id);
        //循环删除
        for (ProductImage productImage : productImages) {

        }*/
        productImageMapper.deleteByProductId(product_id);

    }

    public void update(ProductImage image) {
        productImageMapper.updateByPrimaryKey(image);
    }

    public ProductImage get(Integer id) {
        return productImageMapper.selectByPrimaryKey(id);
    }

    public List<ProductImage> list(Integer product_id) {
        return productImageMapper.selectByExample(product_id);
    }
}
