package com.zhangyan.bio.simple2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2020/05/05/1:01 上午
 * @Description:
 */
public class SimpleServer2 {
    private ServerSocket server;
    private int port = 5020;

    public SimpleServer2() {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
        }
    }

    public void talk() {
        System.out.println("监控端口：" + port);
        Socket socket = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        DataInputStream dis = null;
        DataOutputStream dos = null;
        while (true) {
            try {
                // 阻塞等待，每接收到一个请求就创建一个新的连接实例
                socket = server.accept();
                System.out.println("连接客户端地址：" + socket.getRemoteSocketAddress());

                // 装饰流BufferedReader封装输入流（接收客户端的流）
                bis = new BufferedInputStream(
                        socket.getInputStream());
                bos = new BufferedOutputStream(socket.getOutputStream());


                dis = new DataInputStream(bis);
                dos = new DataOutputStream(bos);

                byte[] bytes = new byte[1]; // 一次读取一个byte
                String ret = "";
                while (dis.read(bytes) != -1) {
                    ret += bytesToHexString(bytes) + " ";
                    if (dis.available() == 0) { //一个请求
                        doSomething(ret);
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    byte[] bytes = new byte[1];
                    dis.read(bytes);
                    System.out.println();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    dos.write(111);
                    dos.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    byte[] bytes = new byte[1];
                    dis.read(bytes);
                    System.out.println();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    public static void doSomething(String ret) {
        System.out.println(ret);
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String BytesHexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    public static void main(String[] args) {
        SimpleServer2 server = new SimpleServer2();
        server.talk();
    }
}
