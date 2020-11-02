package com.zhangyan.nio.reactor.singlethread.eventhandles;

import java.nio.channels.SelectionKey;

/**
 * Created by zhangyan on 2019/2/5
 */
public interface EventHandler {

    void process(SelectionKey key);

}
