package chatroom.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 作用：与服务器端通信
 * 它是多线程的：这是一个聊天室 我们不希望发完一次就结束
 */
public class ClientTask implements Runnable{
    Socket socket;
    //实现绑定
    public ClientTask(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        while (true){
            try {

                /**
                 * 字节流转字符流
                 */
                //发送消息给服务器 这里它是作为一个输出流
                //打印输出流 true自动刷新
                PrintWriter outmsg = new PrintWriter(socket.getOutputStream(),true);
                Scanner scanner = new Scanner(System.in);
                outmsg.println(scanner.nextLine());

                //接收来自服务器端的消息
                //构建输入流
                BufferedReader inmsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("服务器说"+inmsg.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
