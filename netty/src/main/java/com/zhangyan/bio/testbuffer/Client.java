package com.zhangyan.bio.testbuffer;

import java.io.*;
import java.net.Socket;

/**
 * Created by zhangyan on 2019/3/31
 */
public class Client {
//    public static void main(String[] args) throws IOException {
//        Socket socket = new Socket("localhost", 1883);
//        System.out.println("sendBuf=" + socket.getSendBufferSize() + ", recvBuf=" + socket.getReceiveBufferSize());
//
//        InputStream inputStream = socket.getInputStream();
////        OutputStream outputStream = socket.getOutputStream();
////        PrintWriter printWriter = new PrintWriter(outputStream);
//
//        while (true) {
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String readLine = bufferedReader.readLine();
//            System.out.println("client---> time=" + System.currentTimeMillis() + ", msg=" + readLine);
//        }
////        outputStream.close();
////        socket.close();
//    }

    public static void main(String[] args) {
        System.out.println("longjia".hashCode() % 64);
    }
}





















