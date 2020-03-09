package com.zfj.controller;

import com.github.pagehelper.PageHelper;
import com.zfj.pojo.*;
import com.zfj.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author zfj
 * @create 2020/2/29 23:06
 */
@Api(description = "前台控制器")
@Controller
public class ForeController {

    @Autowired
    CategoryService categoryService;//分类

    @Autowired
    ReferalLinkService referalLinkService;//超链接

    @Autowired
    ProductService productService;//产品

    @Autowired
    PropertyValueService propertyValueService;//属性值

    @Autowired
    ReviewService reviewService;//评价

    @Autowired
    UserService userService;//用户

    @Autowired
    OrderItemService orderItemService;//订单项

    @Autowired
    OrderService orderService;//订单


    //首页访问方法，给首页的JSP页面添加以下数据：
    @ApiOperation("首页")
    @RequestMapping("/home")
    public String home(Model model){

        List<Category> categories = categoryService.list();
        productService.fill(categories);
        productService.fillByRow(categories);

        List<ReferalLink> links = referalLinkService.listAll();
        model.addAttribute("categories",categories);
        model.addAttribute("links",links);

        return "index";
    }

    //商品展示
    @ApiOperation("商品展示")
    @RequestMapping("/showProduct")
    public String showProduct(Model model, Integer product_id) {
        Product product = productService.get(product_id);
        productService.setReviewCount(product);
        model.addAttribute("product", product);
        List<PropertyValue> propertyValues = propertyValueService.listByProductId(product_id);
        model.addAttribute("propertyValues", propertyValues);
        List<Review> reviews = reviewService.listByProductId(product_id);
        model.addAttribute("reviews", reviews);
        return "product";
    }

    //查询商品
    @ApiOperation("查询商品")
    @RequestMapping("/searchProduct")
    public String searchProduct(Model model, String keyword) {

        PageHelper.offsetPage(0, 20);
        //》》》》》》》》》》》》》》关键字《待完成》《《《《《《《《《《《《《《《
        List<Product> products = productService.search(keyword);
        for (Product product : products) {
            product.setReviewCount(reviewService.getCount(product.getId()));
        }
        model.addAttribute("products", products);
        return "searchResult";
    }

    //排序
    /*
    @RequestMapping("sortProduct")
    public String sortProduct(Model model, String sort, String keyword) {

        List<Product> products = productService.search(keyword);
        for (Product product : products) {
            product.setReviewCount(reviewService.getCount(product.getId()));
        }
        if(null != sort){
            switch (sort){
                case "all":
                    Collections.sort(products, Comparator.comparing(Product::getSaleXReviewCount));
                    break;
                case "reviewCount":
                    Collections.sort(products, Comparator.comparing(Product::getReviewCount));
                    break;
                case "date":
//					Collections.sort(products, comparing(Product::get));
                    break;
                case "sale":
                    Collections.sort(products, Comparator.comparing(Product::getSale));
                    break;
                case "price":
                    Collections.sort(products, Comparator.comparing(Product::getPrice));
                    break;
            }
        }
        model.addAttribute("products", products);

        return "searchResult";
    }

    */

    //登录页
    @ApiOperation("用户登录")
    @RequestMapping("/login")
    public String login(Model model,
                        @RequestParam("name") String name,
                        @RequestParam("password") String password,
                        HttpSession session) {

        User user = userService.get(name, password);
        if(user == null){
            model.addAttribute("msg","账号或密码有误");
            return "loginPage";
        }
        session.setAttribute("user",user);

        //购物车数量问题测试，
//        int aaaaa=888;
//        model.addAttribute("aaaaa",aaaaa);


        return "redirect:home";

    }

    //注销
    @ApiOperation("用户注销")
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:home";
    }

    //注册
    @ApiOperation("用户注册")
    @RequestMapping("/register")
    public String register(Model model, User user) {
        String name = user.getName();
        boolean exist = userService.isExist(name);

        if (exist) {
            String msg = "用户名已经被占用，不能使用";
            model.addAttribute("msg", msg);
            model.addAttribute("username", user.getName());
            return "registerPage";
        }
        userService.add(user);

        return "redirect:registerSuccessPage";
    }

    /**
     * 立即购买（即新增OrderItem项）需要考虑以下两种情况：
     * 1.如果这个产品已经存在于购物车中，那么只需要相应的调整数量就可以了
     * 2.如果不存在对应的OrderItem项，那么就新增一个订单项（OrderItem）
     * - 前提条件：已经登录
     *
     * @param product_id 产品的ID
     * @param number     购买的数量
     * @param session    session用于获取user信息
     * @return
     */
    @ApiOperation("立即购买")
    @RequestMapping("/buyone")
    public String buyone(Integer product_id, Integer number, HttpSession session) {

        Product product = productService.get(product_id);
        int orderItemId=0;
        User user = (User) session.getAttribute("user");
        boolean found=false;

        List<OrderItem> orderItems = orderItemService.listByUserId(user.getId());
        //遍历该用户的订单里有没有这个商品
        //如果有，则增加相应的数量
        for (OrderItem orderItem : orderItems) {
            if(orderItem.getProduct_id().intValue() == product.getId().intValue()){
                orderItem.setNumber(orderItem.getNumber() + number);
                orderItemService.update(orderItem);
                orderItemId = orderItem.getId();
                break;
            }
        }

        //如果未找到，则创建相应的订单项
        if (!found) {
            OrderItem orderItem = new OrderItem();
            orderItem.setUser_id(user.getId());
            orderItem.setNumber(number);
            orderItem.setProduct_id(product_id);
            orderItemService.add(orderItem);
            orderItemId = orderItem.getId();
        }

        return "redirect:buy?orderItemId=" + orderItemId;

    }

    //购买
    @ApiOperation("购买/结算")
    @RequestMapping("buy")
    public String buy(Model model, String[] orderItemId, HttpSession session) {


        /*for (String s : orderItemId) {
            System.out.println(">>>>>>>>>>>>>>>"+s+"<<<<<<<<<<<");
        }
        OrderItem oi = orderItemService.getById(22);
        Product p = productService.get(oi.getProduct_id());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>**>>>"+oi+"<<<<<<<<<<<<<<");
        System.out.println(">>>>>>>>>>>数量>"+oi.getNumber()+"<<<<<<<<<<<<<<");
        System.out.println(">>>>>>>>>>>单价>"+p.getPrice()+"<<<<<<<<<<<<<<");
//        System.out.println(">>>>>"+oi.getProduct().getPrice()+"；"+oi.getNumber());


        return null;*/

        List<OrderItem> orderItems=new ArrayList<OrderItem>();
        float total=0;

        Product p = new Product();

        //计算总价
        for (String strId : orderItemId) {
            int id=Integer.parseInt(strId);
            OrderItem oi = orderItemService.getById(id);
            //获取该订单项的商品
            p = productService.get(oi.getProduct_id());
            total += p.getPrice() * oi.getNumber();
            orderItems.add(oi);
        }

        session.setAttribute("orderItems", orderItems);
        model.addAttribute("total", total);

        return "buyPage";

    }

    //创建订单
    @ApiOperation("创建订单")
    @RequestMapping("createOrder")
    public String createOrder(Model model, Order order, HttpSession session) {

        //获取当前用户
        User user = (User) session.getAttribute("user");
        //时间格式
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        //设置order
        order.setOrder_code(orderCode);
        order.setCreate_date(new Date());
        order.setUser_id(user.getId());
        order.setStatus(OrderService.waitPay);

        System.out.println(">>>>>>>>>>>>>>order");


        //获取当前订单项
        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems");

        //计算该订单的总价
        float total = orderService.add(order, orderItems);

        //bug发现：在支付页面，order_id为null，但数据库里的id存在不为null，所以此处应再从数据库里取一次
        //order_id，用有唯一性的code取。
        int idByCode = orderService.getIdByCode(order.getOrder_code());

//        return "redirect:payPage?order_id=" + order.getId() + "&total=" + total;
        return "redirect:payPage?order_id=" + idByCode + "&total=" + total;
    }

    //已支付
    @ApiOperation("已支付")
    @RequestMapping("payed")
    public String payed(int order_id, float total, Model model) {
        Order order = orderService.get(order_id);
        //修改订单的状态为 等待发货
        order.setStatus(OrderService.waitDelivery);
        order.setPay_date(new Date());
        orderService.update(order);
        model.addAttribute("o", order);
        return "payed";
    }

    /**
     * 加入购物车方法，跟buyone()方法有些类似，但返回不同
     * 仍然需要新增订单项OrderItem，考虑两个情况：
     * 1.如果这个产品已经存在于购物车中，那么只需要相应的调整数量就可以了
     * 2.如果不存在对应的OrderItem项，那么就新增一个订单项（OrderItem）
     * - 前提条件：已经登录
     *
     * @param product_id
     * @param num
     * @param model
     * @param session
     * @return
     */
    @ApiOperation("加入购物车")
    @RequestMapping("addCart")
    @ResponseBody
    public String addCart(int product_id, int num, Model model, HttpSession session) {

        Product product = productService.get(product_id);
        User user = (User) session.getAttribute("user");
        boolean found=false;

        List<OrderItem> items = orderItemService.listByUserId(user.getId());


        for (OrderItem item : items) {
            if(item.getProduct_id() == product.getId()){
                System.out.println("<<<<<<<<该商品已在购物车里，增加数量>>>>>>>>>>>>");
                item.setNumber(item.getNumber()+num);
                orderItemService.update(item);
                found= true;
                break;
            }
        }

        //?测试时发现问题，在购物车未有商品时，点击添加后order_id的传入值时null
        if (!found) {
            System.out.println(">>>>>>>>>>>>>>>>>未有商品时<<<<<<<<<<<<<<<<<<<");
            OrderItem oi = new OrderItem();
            oi.setUser_id(user.getId());
            oi.setNumber(num);
            oi.setProduct_id(product_id);
            orderItemService.add(oi);
        }
        System.out.println(">>>>>>>>>>>>the found:"+found);

        return "success";

    }

    /**
     * 查看购物车方法：
     * 1.首先通过session获取到当前的用户
     * 2.获取这个用户关联的订单项的集合
     *
     * @param model
     * @param session
     * @return
     */
    @ApiOperation("查看购物车")
    @RequestMapping("/cart")
    public String cart(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");

        //根据用户id获取购物车
        List<OrderItem> ois = orderItemService.listForCart(user.getId());

        for (OrderItem orderItem : ois) {
            System.out.println(">>>>发现bug，已经完成了付款的购物车商品" +
                    "，在显示购物车数量时不应该被计算进来，因为我们的底层查询是" +
                    "根据用户id和订单号为null的订单项，");
        }// 底层和逻辑代码没错，因该是前台页面的问题，显示给前台页面【购物车件数】的数据应再重新筛选。
        model.addAttribute("orderItems",ois);


        return "cart";

    }

    //检查登录状态
    @ApiOperation("检查登录状态")
    @RequestMapping("/checkLogin")
    @ResponseBody
    public String checkLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (null != user)
            return "success";
        return "fail";
    }

    //修改订单
    @ApiOperation("修改订单")
    @RequestMapping("changeOrderItem")
    @ResponseBody
    public String changeOrderItem(Model model, HttpSession session, int product_id, int number) {

        User user = (User) session.getAttribute("user");
        if(null == user)
            return "fail";

        List<OrderItem> ois = orderItemService.listForCart(user.getId());
        for (OrderItem oi : ois) {
            if (oi.getProduct().getId().intValue() == product_id) {
                oi.setNumber(number);
                orderItemService.update(oi);
                break;
            }
        }
        return "success";

    }

    @ApiOperation("删除订单")
    @RequestMapping("deleteOrderItem")
    @ResponseBody
    public String deleteOrderItem(Model model, HttpSession session, Integer orderItemId) {

        User user = (User) session.getAttribute("user");
        if (null == user)
            return "fail";
        orderItemService.delete(orderItemId);
        return "success";

    }

    @ApiOperation("已购买")
    @RequestMapping("bought")
    public String bought(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.list(user.getId(), OrderService.delete);
        orderItemService.fill(orders);
        model.addAttribute("orders", orders);

        return "bought";
    }

    //确认支付
    @ApiOperation("确认支付")
    @RequestMapping("confirmPay")
    public String confirmPay(Model model, Integer order_id) {
        Order order = orderService.get(order_id);
        orderItemService.fill(order);
        model.addAttribute("order", order);
        return "confirmPay";
    }

    //确认订单
    @ApiOperation("确认订单")
    @RequestMapping("orderConfirmed")
    public String orderConfirmed(Model model, Integer order_id) {
        Order o = orderService.get(order_id);
        o.setStatus(OrderService.waitReview);
        o.setConfirm_date(new Date());
        orderService.update(o);
        return "orderConfirmedPage";
    }

    //删除订单
    @ApiOperation("删除订单")
    @RequestMapping("deleteOrder")
    @ResponseBody
    public String deleteOrder(Model model, Integer order_id) {
        Order o = orderService.get(order_id);
        o.setStatus(OrderService.delete);
        orderService.update(o);
        return "success";
    }

    //评论
    @ApiOperation("评论")
    @RequestMapping("review")
    public String review(Model model, Integer order_id) {
        Order order = orderService.get(order_id);
        orderItemService.fill(order);
        Product product = order.getOrderItems().get(0).getProduct();
        List<Review> reviews = reviewService.listByProductId(product.getId());
        productService.setReviewCount(product);
        model.addAttribute("product", product);
        model.addAttribute("order", order);
        model.addAttribute("reviews", reviews);
        return "reviewPage";
    }

    //写评论
    @ApiOperation("写评论")
    @RequestMapping("doreview")
    public String doreview(Model model, HttpSession session,
                           @RequestParam("order_id") Integer order_id,
                           @RequestParam("product_id") Integer product_id,
                           String content) {

        Order order = orderService.get(order_id);
        order.setStatus(OrderService.finish);
        orderService.update(order);

        User user = (User) session.getAttribute("user");
        Review review = new Review();
        review.setContent(content);
        review.setProduct_id(product_id);
        review.setCreateDate(new Date());
        review.setUser_id(user.getId());
        reviewService.add(review);

        return "redirect:review?order_id=" + order_id + "&showonly=true";
    }



}
