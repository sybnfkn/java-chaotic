package com.zhangyan.tx.mapper;

import com.zhangyan.tx.domain.CouponActivity;
import org.apache.ibatis.annotations.*;

/**
 * 流量券活动mapper组件
 * @author zhangyan
 *
 */
@Mapper
public interface CouponActivityMapper {

	/**
	 * 查询流量套餐绑定的状态处于"进行中"的流量券活动
	 * @return 流量券活动
	 */
	@Select("SELECT * "
			+ "FROM coupon_activity "
			+ "WHERE data_package_id=#{dataPackageId} "
			+ "AND status=2")  
	@Results({
		@Result(column = "data_package_id", property = "dataPackageId"),
		@Result(column = "coupon_amount", property = "couponAmount"),
		@Result(column = "start_time", property = "startTime"),
		@Result(column = "end_time", property = "endTime"),
		@Result(column = "created_time", property = "createdTime"),
		@Result(column = "modified_time", property = "modifiedTime")
	})
	CouponActivity queryByDataPackageId(
            @Param("dataPackageId") Long dataPackageId);


	@Insert("insert into coupon_activity (data_package_id, coupon_amount) values (#{dataPackageId}, #{couponAmount})")
	int insert(CouponActivity couponActivity);
	
}
