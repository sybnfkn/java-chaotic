package com.zhangyan.nio.model.singlethread.eventhandles;

import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by zhangyan on 2019/2/5
 */
public class AcceptEventHandlerImpl implements EventHandler {

    private boolean runWithThreadpoll = false;

    public AcceptEventHandlerImpl(boolean runWithThreadpoll) {
        this.runWithThreadpoll = runWithThreadpoll;
    }

    @Override
    public void process(SelectionKey key) {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        try {
            SocketChannel sc = ssc.accept();
            if (sc != null) {
                sc.configureBlocking(false);
                sc.register(key.selector(), SelectionKey.OP_READ,
                        new EventHandlerImpl(runWithThreadpoll));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
