package com.ets.common;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
    public static void main(String[] args) {
        int port = 5555;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务端启动....等待连接！");
            Socket socket = serverSocket.accept();
            //建立好后，从socket获取输入流，并建立缓存区读取
            InputStream inputStream = socket.getInputStream();
            byte [] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            if((len = inputStream.read(bytes)) != -1){
                sb.append(new String(bytes, 0, len,"UTF-8"));
            }
            System.out.println("客户端发送的消息: " + sb);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("您好，客户端！".getBytes("UTF-8"));
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
