package com.zhangyan.nio.model.multi.eventhandle;

import java.nio.channels.SelectionKey;

/**
 * Created by zhangyan on 2019/2/5
 */
public interface EventHandle {

    void process(SelectionKey key);
}
