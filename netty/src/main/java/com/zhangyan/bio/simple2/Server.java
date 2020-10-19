package com.zhangyan.bio.simple2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author messi
 * @date 2019/9/16 0:18
 */
public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(80);
            System.out.println("服务端启动...");
            while (true) {
                socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (Exception e) {

        } finally {
            if (serverSocket != null) {
                // 关闭流操作
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ServerHandler implements Runnable {

    private Socket socket;
    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("有一个客户端连接上了...");
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String receiveMessage = reader.readLine();
                // 客户端已经关闭
                if (receiveMessage == null)
                    break;
                System.out.println("客户端:" + receiveMessage);
                // 业务请求处理, 返回数据
                writer.println("response");

                // 中断连接
                if ("bye".equals(receiveMessage)) {
                    writer.println("bye");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Thread.sleep(3000);
                writer.println("respons2");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } finally {
            try {
                reader.close();
                writer.close();
                socket.close();
            } catch (Exception e) {}
        }
    }
}
