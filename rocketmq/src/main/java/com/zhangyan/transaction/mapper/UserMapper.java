package com.zhangyan.transaction.mapper;


import com.zhangyan.transaction.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("")
    User findByUserName(String name);

    @Insert("")
    void insert(User user);

    @Select("")
    User selectOne(User user);
}
