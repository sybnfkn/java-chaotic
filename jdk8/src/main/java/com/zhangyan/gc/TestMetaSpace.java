package com.zhangyan.gc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/11/24/6:42 下午
 * @Description:
 */
public class TestMetaSpace {

    public static void main(String[] args) throws ClassNotFoundException {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(UserService.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }
            });

            UserService userService = (UserService) enhancer.create();
            userService.getUser();
        }
    }


    static class UserService {
        public String getUser() {
            return "leo";
        }
    }
}
