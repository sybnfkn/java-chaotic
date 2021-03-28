package com.zhangyan.service;

import com.zhangyan.fegin.UserFeginService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/02/23/9:16 下午
 * @Description:
 */
@RestController
public class UserService implements UserFeginService {

    @Override
    @RequestMapping(value = "/sayHello/{name}",
            method = RequestMethod.GET)
    public String sayHello(@PathVariable("name") String name) {
        System.out.println("..............");
        try {
            Thread.sleep(5 * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello " + name;
    }
}
