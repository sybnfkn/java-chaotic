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


    @Transactional(rollbackFor = Exception.class)
    public void save(CouponActivity couponActivity) {

        couponActivityMapper.insert(couponActivity);
    }

}
