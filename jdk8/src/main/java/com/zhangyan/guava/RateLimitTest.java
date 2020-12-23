package com.zhangyan.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2020/12/05/11:54 上午
 * @Description:
 */
public class RateLimitTest {
    public static void main(String[] args) {
        RateLimiter rl = RateLimiter.create(1);
        while (true) {
            double acquire = rl.acquire(20);

            System.out.println(acquire);
        }
    }
}
