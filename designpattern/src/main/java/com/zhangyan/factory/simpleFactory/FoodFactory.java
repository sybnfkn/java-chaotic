package com.zhangyan.factory.simpleFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/02/12/7:45 下午
 * @Description:
 */
public class FoodFactory {

    public static Food makeFood(String name) {
        if ("huangmen".equals(name)) {
            return new HuangMenFood();
        } else if ("lanzhou".equals(name)) {
            return new LanZhouFood();
        }
        return null;
    }


}
