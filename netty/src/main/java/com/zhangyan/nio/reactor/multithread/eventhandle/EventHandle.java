package com.zhangyan.nio.reactor.multithread.eventhandle;

import java.nio.channels.SelectionKey;

/**
 * Created by zhangyan on 2019/2/5
 */
public interface EventHandle {

    void process(SelectionKey key);
}
