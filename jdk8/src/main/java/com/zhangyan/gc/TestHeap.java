package com.zhangyan.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/11/27/2:26 下午
 * @Description:
 */
public class TestHeap {

    static class OOMObject {}
    public static void main(String[] args) {
        List<OOMObject> vs = new ArrayList<>();
        while (true)
            vs.add(new OOMObject());
    }

}
