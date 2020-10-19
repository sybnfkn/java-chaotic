package com.zhangyan.nio.model.multi;


import share.multi.eventhandle.AcceptEventHandleImpl;
import share.multi.eventhandle.EventHandle;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhangyan on 2019/2/5
 */
public class MainReactor implements Runnable {

    private Selector selector;
    private ServerSocketChannel ssc;

    public MainReactor(ServerSocketChannel ssc) throws IOException {
        this.ssc = ssc;
        // 创建一个多路复用器
        selector = Selector.open();
        // 将当前channel注册到selector上, 将handle以附件形式也注册上去
        ssc.register(selector, SelectionKey.OP_ACCEPT, new AcceptEventHandleImpl());
    }

    // 分发器
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                if (selector.select() > 0) {
                    // 通过操作系统底层的epoll, poll获取相应事件的key
                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        // 忽略无效的selectKey
                        if (!key.isValid()) {
                            continue;
                        }
                        // 明确就是Accept
                        EventHandle handle = (EventHandle) key.attachment();
                        handle.process(key);

                        iterator.remove();
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
