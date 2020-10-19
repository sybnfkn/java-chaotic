package com.zhangyan.bio.simple2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2020/05/05/1:01 上午
 * @Description:
 */
public class SimpleClient2 {
    private Socket socket;
    private SocketAddress address;

    public SimpleClient2() {
        try {
            socket = new Socket();
            address = new InetSocketAddress("127.0.0.1", 5020);
            socket.connect(address, 1000);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void talk() {

        try {

            socket.getInputStream().read();
            //使用DataInputStream封装输入流
//            InputStream os = new DataInputStream(System.in);
//
//            byte [] b = new byte[1];
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//            while (-1 != os.read(b)) {
//                dos.write(b); // 发送给客户端
//            }

//            dos.flush();
//            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {

            }
        }
    }

    public static void main(String[] args) {
        SimpleClient2 client = new SimpleClient2();
        client.talk();
    }
}
