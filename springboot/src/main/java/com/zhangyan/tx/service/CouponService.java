package com.zhangyan.tx.service;

import com.zhangyan.tx.domain.CouponActivity;
import com.zhangyan.tx.mapper.CouponActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/16/3:05 下午
 * @Description:
 */
@Service
public class CouponService {

    @Autowired
    private CouponActivityMapper couponActivityMapper;

    @Autowired
    private CouponAnotherService couponAnotherService;


    @Transactional(rollbackFor = Exception.class)
    public void save(CouponActivity couponActivity) {

        couponActivityMapper.insert(couponActivity);
        // 当前service通过this调用拦截器不生效，理论应该从spring容器中获取当前对象
//        this.save1();
        // 调用外部方法
        couponAnotherService.save();
    }

    /**
     *
     */
    @Transactional(rollbackFor = Exception.class)
    public void save1() {
        CouponActivity couponActivity = new CouponActivity();
        couponActivity.setCouponAmount(200d);
        couponActivity.setDataPackageId(200L);
        couponActivityMapper.insert(couponActivity);
    }

}
