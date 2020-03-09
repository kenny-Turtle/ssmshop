package com.zfj.controller;

import com.zfj.pojo.ReferalLink;
import com.zfj.service.ReferalLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/28 15:47
 */
@Api(description = "超链接管理")
@Controller
@RequestMapping("/admin")
public class ReferalLinkController {

    @Autowired
    ReferalLinkService referalLinkService;

    @ApiOperation("显示所有超链接")
    @RequestMapping("/listLink")
    public String list(Model model){

        List<ReferalLink> referalLinks = referalLinkService.listAll();
        model.addAttribute("links",referalLinks);
        return "admin/listLink";
    }

    @ApiOperation("更新超链接")
    @RequestMapping("/updateLink")
    public String update(ReferalLink link){
        referalLinkService.update(link);
        return "redirect:listLink";
    }

}
