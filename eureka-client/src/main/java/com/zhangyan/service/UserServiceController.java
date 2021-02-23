package com.zhangyan.service;

import com.zhangyan.fegin.UserFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/02/23/9:08 下午
 * @Description:
 */
@Component
public class UserServiceController {

    @Autowired
    private UserFeginService userFeginService;


    @RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET)
    public String greeting(@PathVariable("name") String name) {
        return userFeginService.sayHello(100L, "zhangsan", 123);
    }

}
