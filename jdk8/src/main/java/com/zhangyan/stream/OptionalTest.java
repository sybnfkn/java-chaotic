package com.zhangyan.stream;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2020/12/01/10:37 上午
 * @Description:
 */
public class OptionalTest  {

    public static void main(String[] args) {

        // 1.of
        System.out.println(Optional.of("参数异常").isPresent());

        String name = null;
        // 2.ofNullable
        System.out.println(Optional.ofNullable(name).orElse("zhangsan"));
        System.out.println(Optional.ofNullable(name).isPresent());

        // 3.orElseGet
        System.out.println(Optional.ofNullable(name).orElseGet(() -> {
            // 可以写一些查询接口
            return OptionalTest.getNewName();
        }));

        // 4.orElseThrow 如果是空，直接异常
        System.out.println(Optional.ofNullable(Optional.ofNullable(name).orElseThrow(RuntimeException::new)));

        // 5.filter filter false会直接返回空Optional
        name = "zhangsan";
        System.out.println(Optional.of(name).filter(t -> t.length() > 5).orElse("abc"));

        // 6.map
        Optional.of(name).map(t -> t.toUpperCase()).orElse("to uppercase 失败");

        // 7.ifPresent 如果有值，就把值消费了
        Optional.of(name).ifPresent(t -> System.out.println(t.toLowerCase()));
    }

    public static String getNewName() {
        return "lisi";
    }

}
