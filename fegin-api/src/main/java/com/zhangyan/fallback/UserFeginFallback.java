package com.zhangyan.fallback;

import com.zhangyan.fegin.UserFeginService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/02/6:27 下午
 * @Description:
 */

@Component
public class UserFeginFallback implements FallbackFactory<UserFeginService> {

    private static final Logger logger = LoggerFactory.getLogger("UserFeginFallback");

    @Override
    public UserFeginService create(final Throwable cause) {
        return new UserFeginService() {
            @Override
            public String sayHello(String name) {
                logger.error("fallback exception={}", cause);
                return "fallback";
            }
        };
    }
}
