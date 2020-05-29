package com.tiger.dubbo.controller;

import com.tiger.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2020/5/29
 * @Author tiger
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private UserService userService;

    @RequestMapping("/hello")
    public String sayHello(@RequestParam("name") String name){
        String str = userService.hello("tiger");
        return str;
    }

}
