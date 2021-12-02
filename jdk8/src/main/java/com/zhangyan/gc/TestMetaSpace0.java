package com.zhangyan.gc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/12/02/11:17 上午
 * @Description:
 */
public class TestMetaSpace0 {

    public static void main(String[] args) {

    }

    public static String buildData(Object bean) {
        try {
            SerializeConfig CONFIG = new SerializeConfig();
            CONFIG.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
            return JSON.toJSONString(bean, CONFIG);
        } catch (Exception e) {
            return null;
        }
    }
}
