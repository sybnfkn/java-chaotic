package com.zhangyan.tx.controller;

import com.zhangyan.tx.domain.CouponActivity;
import com.zhangyan.tx.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/16/2:53 下午
 * @Description:
 */
@RestController
public class TestController {


    @Autowired
    private CouponService couponService;

    @RequestMapping("/save")
    public void add() {

        CouponActivity couponActivity = new CouponActivity();
        couponActivity.setCouponAmount(250d);
        couponActivity.setDataPackageId(250L);

        couponService.save(couponActivity);
    }



}
