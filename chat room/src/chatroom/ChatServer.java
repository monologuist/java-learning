package chatroom;

import chatroom.task.ServerTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/*
* 服务器端
* */
public class ChatServer {
    public static void main(String[] args){
        //在端口为8888上创建服务器
        ServerSocket server = null;
        try {
            server = new ServerSocket(8888);
            System.out.println("服务器创建成功");

            while (true){
                //监听等待连接 程序阻塞
                Socket socket = server.accept();
                //只要连接就启动一个线程
                //这样就可以保证服务器端的多线程
                Thread thread = new Thread(new ServerTask(socket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
