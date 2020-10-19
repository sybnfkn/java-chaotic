package com.zhangyan.bio.simple;

import java.io.*;
import java.net.Socket;

/**
 * Created by zhangyan on 2019/3/31
 */
public class ServiceClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8899);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println(outputStream);
        printWriter.write("hello server");
        printWriter.flush();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String readLine = bufferedReader.readLine();
        System.out.println("client--->" + readLine);

        inputStream.close();
        outputStream.close();
        socket.close();
    }
}





















