package com.zfj.service;

import com.zfj.pojo.ProductImage;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/26 14:12
 */
public interface ProductImageService {

    void add(ProductImage image);

    void deleteByProductId(Integer product_id);

    void update(ProductImage image);

    ProductImage get(Integer id);

    List<ProductImage> list(Integer product_id);
}
