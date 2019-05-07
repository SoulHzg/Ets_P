package com.ets.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientTest {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 5555;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        // 建立连接后获得输出流
        OutputStream outputStream = socket.getOutputStream();
        String message="你好，胡志刚！是打发的法师打发打发打发发顺丰大赛的发生的发送方大厦的发生的发生发的发生发生大师傅是的发生发发发发生发的发的发发大水发生大发";
        socket.getOutputStream().write(message.getBytes("UTF-8"));
        //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        byte [] bytes = new byte[1024];
        int len;
        StringBuilder stringBuilder = new StringBuilder();
        while ((len=inputStream.read(bytes))!= -1){
            stringBuilder.append(new String(bytes, 0, len,"UTF-8"));
        }
        System.out.println("服务端返回信息："+stringBuilder.toString());
        outputStream.close();
        socket.close();
    }
}
