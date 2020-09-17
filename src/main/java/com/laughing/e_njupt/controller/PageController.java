package com.laughing.e_njupt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 2020/9/11 13:01
 */
@Controller
@RequestMapping("/page")
public class PageController {
    @RequestMapping("/user")
    public String getPage(){
        return "user";
    }

    @RequestMapping("/order")
    public String getOrder(){
        return "order";
    }

    @RequestMapping("/worker")
    public String getWorker(){
        return "worker";
    }

    @RequestMapping("/table")
    public String getTable(){
        return "table";
    }

    @RequestMapping("/ds")
    public String getDs(){
        return "ds";
    }
}
