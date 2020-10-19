package com.zhangyan.nio.model.multi;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by zhangyan on 2019/2/5
 */
public class Server {
    public static void main(String[] args) throws Exception {
        int port = 8888;

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);

        MainReactor mainReactor = new MainReactor(ssc);
        Thread t = new Thread(mainReactor);
        t.start();

        t.join();
    }
}
