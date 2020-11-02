package com.zhangyan.nio.reactor.singlethread;


import com.zhangyan.nio.reactor.singlethread.eventhandles.EventHandler;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhangyan on 2019/2/5
 */
public class Dispatcher implements Runnable {

    private Selector selector;
    private boolean isRunning;

    public Dispatcher() {
        try {
            selector = Selector.open();
            isRunning = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        isRunning = false;
    }

    public void register(SelectableChannel channel, int ops, EventHandler handler) {

        try {
            channel.register(selector, ops, handler);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(isRunning) {
            try {
                if (selector.select() > 0) {
                    // 事件分发
                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (!key.isValid()) {
                            continue;
                        }
                        EventHandler eventHandler = (EventHandler) key.attachment();
                        eventHandler.process(key);

                        iterator.remove();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

