package com.zhangyan.bio.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangyan on 2019/3/30
 */
public class ServiceServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("localhost", 8899), 1000);

        while (true) {
            Socket socket = server.accept();
            new Thread(new ServiceTask(socket)).start();
        }
    }
}
