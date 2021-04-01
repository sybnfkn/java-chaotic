package com.zhangyan.tx.service;

import com.zhangyan.tx.domain.CouponActivity;
import com.zhangyan.tx.mapper.CouponActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/31/8:14 下午
 * @Description:
 */
@Service
public class CouponAnotherService {

    @Autowired
    private CouponActivityMapper couponActivityMapper;


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void save() {
        CouponActivity couponActivity = new CouponActivity();
        couponActivity.setCouponAmount(300d);
        couponActivity.setDataPackageId(300L);
        couponActivityMapper.insert(couponActivity);
    }


}
