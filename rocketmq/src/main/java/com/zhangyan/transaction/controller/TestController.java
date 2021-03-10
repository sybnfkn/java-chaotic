package com.zhangyan.transaction.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/10/3:18 下午
 * @Description:
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
