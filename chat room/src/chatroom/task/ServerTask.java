package chatroom.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * 作用：不断的给客户端发消息
 * 它是多线程的：这是一个聊天室 我们不希望发完一次就结束
 */
public class ServerTask implements Runnable{
    Socket socket;
    //实现绑定
    public ServerTask(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        //获得远程连接的客户端信息IP+Port（客户机端口）
        System.out.println(socket.getRemoteSocketAddress() + "进入聊天室");

        try {
            /**
             * 构建输入流 客户端先说 只要不为空就可以一直说
             * 字节流转字符流 再转缓冲流
             */
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inmsg;
            while ((inmsg = reader.readLine())!=null){
                System.out.println(socket.getRemoteSocketAddress()+"说"+inmsg);
                /**
                 * 下面服务器说
                 * 客户端信息不为空，则服务器端可以回应
                 * true表示自动刷新
                 */
                PrintWriter outmsg = new PrintWriter(socket.getOutputStream(),true);
                //控制台输入
                Scanner scanner = new Scanner(System.in);
                outmsg.println(scanner.nextLine());
            }
        }catch (SocketException e){
            System.out.println(socket.getRemoteSocketAddress() + "已经退出聊天室");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
