package com.zhangyan.bio.testbuffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangyan on 2019/3/30
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("localhost", 1883), 1000);

        while (true) {
            Socket socket = server.accept();
            System.out.println("sendBuf=" + socket.getSendBufferSize() + ", recvBuf=" + socket.getReceiveBufferSize());
            new Thread(new Task(socket)).start();
        }
    }
}
