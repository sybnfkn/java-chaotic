package com.zhangyan.transaction.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhangyan.transaction.domain.UserDto;
import com.zhangyan.transaction.mapper.UserMapper;
import com.zhangyan.transaction.domain.User;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserRegTransactionListener implements TransactionListener {

    @Autowired
    private UserMapper userMapper;

    /**
     * 如果将这里代码抽取另一个方法中，就可以明确返回commit/rollback
     * @param msg
     * @param arg
     * @return
     */
    @Override
    @Transactional
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // 从消息体中解析出数据
        UserDto userDto = JSON.parseObject(msg.getBody(), UserDto.class);
        User user = new User();
        copy(userDto, user);
        try {
            userMapper.insert(user);
        } catch (Throwable e) {
            e.printStackTrace();
            // 表示系统异常，向客户端返回失败，可通过明确的 ROLLBACK 指令进行判断
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        // 返回 UNKNOW 因为此时事务还没有提交
        // 因为该方法尽管在方法最后一行，但可能发生断电等异常情况，数据库并没有成功。
        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        UserDto userDto = JSON.parseObject(msg.getBody(), UserDto.class);
        User queryModel = new User();
        queryModel.setUsername(userDto.getUserName());
        // 根据 用户名（假设唯一，在其他业务场景下可能是订单编号或业务流水号），如果存在，说明本地事务执行成功，可以提交
        if(userMapper.selectOne(queryModel) != null ) {
            return LocalTransactionState.COMMIT_MESSAGE;
        } else {
            // 此时这里就可以明确返回rollback了
            return LocalTransactionState.UNKNOW;
        }
    }

    private static void copy(UserDto userDto, User user) {
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setSex(userDto.getSex());
        user.setUsername(userDto.getUserName());
    }
}
