package com.zhangyan.factory.simpleFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/02/12/7:45 下午
 * @Description:
 */
public class Client {

    private static FoodFactory foodFactory;

    public static void main(String[] args) {
        foodFactory = new FoodFactory();
        Food food = foodFactory.makeFood("huangmen");
    }
}
