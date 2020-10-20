package com.zhangyan.nio.model.singlethread;


import com.zhangyan.nio.model.singlethread.eventhandles.AcceptEventHandlerImpl;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by zhangyan on 2019/2/5
 */
public class Server {

    public static void main(String[] args) throws Exception {
        Dispatcher dispatcher = new Dispatcher();

        int port = 8888;

        // 类似一个severSocket
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);

        // 注册"accept", 类似于accept, 注意不阻塞在这里, bio会阻塞在accept上
        dispatcher.register(ssc, SelectionKey.OP_ACCEPT, new AcceptEventHandlerImpl(true));

        // start event loop 一直循环等待事件
        Thread thread = new Thread(dispatcher);
        thread.run();

        // main线程等待子线程执行完毕
        thread.join();
    }
}
