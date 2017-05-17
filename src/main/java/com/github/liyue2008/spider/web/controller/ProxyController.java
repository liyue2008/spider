package com.github.liyue2008.spider.web.controller;

import com.github.liyue2008.spider.core.service.ProxyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by liyue on 2017/5/13.
 */
@Controller
public class ProxyController {

    @Resource
    private ProxyService proxyService;
    @RequestMapping("/proxy-list.html")
    public String list(Model model){

        model.addAttribute("proxies",proxyService.getAllProxies());
        model.addAttribute("classActiveProxyList","active");
        model.addAttribute("title","代理列表");

        return "proxy-list";
    }
}
