package com.zhangyan.nio.reactor.multithread;


import com.zhangyan.nio.reactor.multithread.eventhandle.EventHandle;
import com.zhangyan.nio.reactor.multithread.eventhandle.IOEventHandleImpl;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhangyan on 2019/2/5
 */
public class SubReactor implements Runnable {
    private Selector selector;

    public SubReactor() throws IOException {
        this.selector = Selector.open();
    }
    public void registerChannel(SocketChannel sc) throws Exception {
        sc.register(selector,
                SelectionKey.OP_READ | SelectionKey.OP_CONNECT,
                new IOEventHandleImpl());
    }

    @Override
    public void run() {
        while (true) {
            try {
                //每个SubReactor 自己做事件分派处理读写事件
                if (selector.select() > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        EventHandle handle = (EventHandle) key.attachment();

                        handle.process(key);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
