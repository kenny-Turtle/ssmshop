package com.zfj.service;

import com.zfj.mapper.ProductMapper;
import com.zfj.pojo.Category;
import com.zfj.pojo.Product;
import javafx.scene.web.PromptData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/25 17:39
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ReviewService reviewService;

    public void add(Product product) {
        productMapper.insert(product);
    }

    public void delete(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    public void update(Product product) {
        productMapper.updateByPrimaryKey(product);
    }

    public Product get(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    //根据分类id获取所有产品
    public List<Product> list(Integer category_id) {
        List<Product> products = productMapper.selectByCategoryId(category_id);
        return products;
    }

    //  对多个分类进行产品填充
    public void fill(List<Category> categories) {
        for (Category category : categories) {
            fill(category);
        }

    }

    //  对某一分类进行产品填充
    public void fill(Category category) {
        List<Product> products = list(category.getId());
        category.setProducts(products);

    }

    //为多个分类填充产品集合
    public void fillByRow(List<Category> categories) {

        //规定每行的产品数
        int productNumberOfEachRow = 8;
        //对所有分类进行遍历
        for (Category category : categories) {
            //拿到一个分类里的所有产品
            List<Product> products = category.getProducts();
//            List<List<Product>> productByRow = new ArrayList<>();
            List<List<Product>> productByRow=new ArrayList<List<Product>>();
            /*for (int i = 0; i < products.size(); i += productNumberOfEachRow) {
                int size = i + productNumberOfEachRow;
                size = size > products.size() ? products.size() : size;
                List<Product> productsOfEachRow = products.subList(i, size);
                productByRow.add(productsOfEachRow);
            }*/
            for(int i=0;i<products.size();i += productNumberOfEachRow){
                int size = i + productNumberOfEachRow;
                size = size > products.size() ? products.size() : size;
                List<Product> productsOfEachRow = products.subList(i, size);
                productByRow.add(productsOfEachRow);
            }


            category.setProductByRow(productByRow);
        }
    }

    //给产品设置评论数  为产品填充ReviewCount字段
    public void setReviewCount(Product product) {
        int count = reviewService.getCount(product.getId());
        product.setReviewCount(count);
    }

    //关键字查找
    public List<Product> search(String keyword) {
        return null;
    }
}
