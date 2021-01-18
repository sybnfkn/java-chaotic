package com.zhangyan.bio.testbuffer;


import java.io.*;
import java.net.Socket;

/**
 * Created by zhangyan on 2019/3/30
 */
public class Task implements Runnable {
    private Socket socket;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;

    public Task(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            outputStream = socket.getOutputStream();
            while (true) {
                Thread.sleep(200);

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write("hello server hello servervhello " +
                        "serverhello serverhello serverhello " +
                        "serverhello serverhello serverhello " +
                        "serverhello serverhello serverhello " +
                        "serverhello serverhello serverhello serverhello " +
                        "serverhello serverhello serverhello serverhello serverhello" +
                        " serverhello serverhello serverhello serverhello serverhello serverhello " +
                        "serverhello serverhello serverhello serverhello serverhello serverhello " +
                        "serverhello serverhello serverhello serverhello serverhello" +
                        " serverhello serverhello serverhello server" + "\n");
                bufferedWriter.flush();

//                PrintWriter printWriter = new PrintWriter(outputStream);
//                printWriter.flush();
//                System.out.println("111");
            }
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

















