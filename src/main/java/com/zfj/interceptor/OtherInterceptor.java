package com.zfj.interceptor;

import com.zfj.pojo.OrderItem;
import com.zfj.pojo.User;
import com.zfj.service.CategoryService;
import com.zfj.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author zfj
 * @create 2020/3/2 12:55
 */
public class OtherInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        //获取购物车中一共有多少数量
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int cartTotalItemNumber=0;
        if(null != user){
            List<OrderItem> items = orderItemService.listByUserId(user.getId());
            for (OrderItem item : items) {
                cartTotalItemNumber += item.getNumber();
            }
        }
        session.setAttribute("cartTotalItemNumber",cartTotalItemNumber);

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


    }
}
