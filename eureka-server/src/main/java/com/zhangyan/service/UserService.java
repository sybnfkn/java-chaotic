package com.zhangyan.service;

import com.zhangyan.fegin.UserFeginService;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/02/23/9:16 下午
 * @Description:
 */
@Component
public class UserService implements UserFeginService {
    @Override
    public String sayHello(Long id, String name, Integer age) {
        System.out.println();
        return "hello " + name;
    }
}
