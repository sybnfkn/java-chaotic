package com.zhangyan;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/07/20/5:21 下午
 * @Description:
 */
@SpringBootApplication
@EnableDubboConfiguration
public class DubboClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboClientApplication.class, args);
    }
}
