package com.zhangyan.nio.reactor.multithread.eventhandle;



import com.zhangyan.nio.reactor.multithread.SubReactor;

import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by zhangyan on 2019/2/5
 */
public class AcceptEventHandleImpl implements EventHandle {

    private int workCount = Runtime.getRuntime().availableProcessors();
    private SubReactor[] subReactors = new SubReactor[workCount];
    volatile int nextHandle = 0;

    public AcceptEventHandleImpl() {
        init();
    }

    private void init() {
        nextHandle = 0;
        try {
            for (int i = 0; i < workCount; i++) {
                subReactors[i] = new SubReactor();
                // 依次并启动
                Thread t = new Thread(subReactors[i]);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel c = ssc.accept();
            if (c != null) {// 注册读写
                synchronized (c) {
                    // 顺序获取SubReactor，然后注册channel
                    SubReactor work = subReactors[nextHandle];
                    work.registerChannel(c);
                    nextHandle++;
                    if (nextHandle >= subReactors.length) {
                        nextHandle = 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
