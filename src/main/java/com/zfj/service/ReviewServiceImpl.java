package com.zfj.service;

import com.zfj.mapper.ReviewMapper;
import com.zfj.mapper.UserMapper;
import com.zfj.pojo.Review;
import com.zfj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/25 17:43
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private UserService userService;

    public void add(Review review) {
        reviewMapper.insert(review);
    }

    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    public void update(Review review) {
        reviewMapper.updateByPrimaryKey(review);
    }

    public Review get(int id) {
        System.out.println(">>>>>>>>>>>>>:debug here:<<<<<<<<<<<<<<");
        return reviewMapper.selectByPrimaryKey(id);
    }

    //根据产品id获取所有的评论
    public List<Review> listByProductId(Integer product_id) {
        List<Review> reviews = reviewMapper.selectByProductId(product_id);
        setUser(reviews);
        return reviews;
    }

    //将产品评论与用户做一个绑定
    public void setUser(List<Review> reviews) {
        for (Review review : reviews) {
            setUser(review);
        }
    }

    private void setUser(Review review) {
        int user_id = review.getUser_id();
        User user = userService.get(user_id);
        review.setUser(user);
    }


    //返回当前产品下评论的数量
    public int getCount(int product_id) {
        return listByProductId(product_id).size();
    }
}
