package com.zfj.interceptor;

import com.zfj.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 登录拦截器
 * @Author zfj
 * @create 2020/3/2 12:46
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //不用认证的页面
        String[] noNeedAuthPage = new String[]{
                "/home",
                "/searchProduct",
                "/sortProduct",
                "/showProduct",
                "/loginPage",
                "/login",
                "/registerPage",
                "/register",
                "/registerSuccessPage",
                "/test",
                "/checkLogin",
                "/admin"
        };


        String uri = request.getRequestURI();
        if(!Arrays.asList(noNeedAuthPage).contains(uri)){

            User user = (User) session.getAttribute("user");
            if(user == null){//如果是未登录用户
                response.sendRedirect("/loginPage");
                return false;//该请求不放行
            }
        }
        //放行
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
