package com.zhangyan.bio.simple2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2020/05/04/11:43 下午
 * @Description:
 */
public class SimpleServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(80);
            System.out.println("服务端启动...");
            while (true) {
                socket = serverSocket.accept();
                new Thread(new SimpleServerHandler(socket)).start();
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

class SimpleServerHandler implements Runnable {

    private Socket socket;
    public SimpleServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();

            while (true) {
                byte[] bytes = new byte[1];
                int result = is.read(bytes, 0, bytes.length);
                // 客户端已经关闭
//                if (receiveMessage == null)
//                    break;
                // 业务请求处理, 返回数据
                os.write(1);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();

            try {
                os.write(2);
                os.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                int result = is.read();
                System.out.println();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } finally {
            try {
                is.close();
                os.close();
                socket.close();
            } catch (Exception e) {}
        }
    }
}
