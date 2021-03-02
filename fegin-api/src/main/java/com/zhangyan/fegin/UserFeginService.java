package com.zhangyan.fegin;

import com.zhangyan.fallback.UserFeginFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/02/23/8:54 下午
 * @Description:
 */
@FeignClient(name = "EUREKA-SERVER", fallbackFactory = UserFeginFallback.class)
public interface UserFeginService {

    @RequestMapping(value = "/sayHello/{name}", method = RequestMethod.GET)
    String sayHello(@PathVariable("name") String name);


}
