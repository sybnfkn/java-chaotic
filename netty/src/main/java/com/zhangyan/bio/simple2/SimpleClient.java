package com.zhangyan.bio.simple2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2020/05/04/11:43 下午
 * @Description:
 */
public class SimpleClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
//        socket.setSoTimeout(3000);
        socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 80));
        System.out.println("连接已建立...");
        // 和serverSocket建立的reader和writer
        BufferedReader reader = new BufferedReader
                (new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader br = null;
        while (true) {
            // 和键盘输入流建立的reader
//            br = new BufferedReader(new InputStreamReader(System.in));
//            String message = br.readLine();
//            System.out.println("我:" + message);
//            // 发送给服务端
//            writer.println(message);
            // 不断从服务端接受到消息, 直到bye消息
            String receiveMessage = reader.readLine();
            System.out.println("服务器:" + receiveMessage);
            if ("bye".equals(receiveMessage)) {
                break;
            }
        }
        // 关闭流
        reader.close();
        writer.close();
        br.close();
        socket.close();
    }
}
