package chatroom;


import chatroom.task.ClientTask;
import chatroom.task.ServerTask;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端
 */
public class ChatClient {
    public static void main(String[] args) {
        //连接服务器,地址为服务器IP地址,端口为服务器的启动端口
        //127.0.0.1=localhost
        try {
            //连接后产生随机端口
            Socket socket = new Socket("127.0.0.1", 8888);
            //只要一连接就启动新的线程进行通信
            Thread thread = new Thread(new ClientTask(socket));
            thread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
