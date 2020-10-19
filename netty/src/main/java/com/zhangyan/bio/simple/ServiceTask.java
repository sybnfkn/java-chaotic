package com.zhangyan.bio.simple;

import java.io.*;
import java.net.Socket;

/**
 * Created by zhangyan on 2019/3/30
 */
public class ServiceTask implements Runnable {
    private Socket socket;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;

    public ServiceTask(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String readLine = bufferedReader.readLine();
            System.out.println("server--->" + readLine);

            String clientStr = "hello client";
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println(clientStr);
            printWriter.flush();
        } catch (Exception e) {

        } finally {
            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

















