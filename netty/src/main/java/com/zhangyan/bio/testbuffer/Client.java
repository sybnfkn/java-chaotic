package com.zhangyan.bio.testbuffer;

import java.io.*;
import java.net.Socket;

/**
 * Created by zhangyan on 2019/3/31
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1883);
        System.out.println("sendBuf=" + socket.getSendBufferSize() + ", recvBuf=" + socket.getReceiveBufferSize());

//        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);

        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printWriter.println(outputStream);
            printWriter.write("hello server hello servervhello " +
                    "serverhello serverhello serverhello " +
                    "serverhello serverhello serverhello " +
                    "serverhello serverhello serverhello " +
                    "serverhello serverhello serverhello serverhello " +
                    "serverhello serverhello serverhello serverhello serverhello" +
                    " serverhello serverhello serverhello serverhello serverhello serverhello " +
                    "serverhello serverhello serverhello serverhello serverhello serverhello " +
                    "serverhello serverhello serverhello serverhello serverhello" +
                    " serverhello serverhello serverhello server");
            printWriter.flush();
            System.out.println(".......time=" + System.currentTimeMillis());
        }


//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//        String readLine = bufferedReader.readLine();
//        System.out.println("client--->" + readLine);

//        outputStream.close();
//        socket.close();
    }
}





















