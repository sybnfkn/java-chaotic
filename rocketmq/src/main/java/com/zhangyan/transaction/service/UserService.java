package com.zhangyan.transaction.service;

import com.zhangyan.transaction.domain.UserDto;

import java.util.Map;

public interface UserService {

    public Map<String, Object> reg(UserDto userDto);
}
