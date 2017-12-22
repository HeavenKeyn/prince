package example.client;

import example.MsgProtocol;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * Created by Heavenkenyn on 2017/4/16.
 */

public class TCPSocket {
    private PrintStream printStream;

    public TCPSocket(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
					/* 连接服务器 */
                    Socket socket = new Socket("192.168.233.1", 1984);
				    /* 获取输出流 */
                    printStream = new PrintStream(socket.getOutputStream(), true, "utf-8");
                    /*BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String str;
                    while((str=br.readLine())!=null){
                        System.out.println(str);
                        accept(str);
                        System.out.println("Please input command");
                    }*/
                    InputStream inputStream = socket.getInputStream();
                    byte buffer[] = new byte[2048];
                    int temp = 0;
                    // 从InputStream当中读取客户端所发送的数据
                    while ((temp = inputStream.read(buffer)) != -1) {
                        String message = new String(buffer, 0, temp);
                        if (message!=null&&message.length()>0&&!message.equals(MsgProtocol.CRLF)){
                            System.out.println(message);
                            accept(message);
                            System.out.println("Please input command");
                        }
                    }
                } catch (Exception e) {
                    //handleException(e, "io exception: " + e.toString());
                }
            }

        }).start();
    }

    public void send(String msg){
        if (printStream != null){
            printStream.println(msg);
            printStream.flush();
        }
    }

    private void accept(String message){
        String[] values = message.split(MsgProtocol.CRLF,2);
        int code;
        try {
            code = Integer.parseInt(values[0]);
        }catch (NumberFormatException e){
            code = MsgProtocol.ERROR;
        }
        if (code == MsgProtocol.SUCCESS){
            if (values[1].length()>0){
                System.out.println(values[1]);
            }else {
                System.out.println("OK!");
            }
        }else if (code == MsgProtocol.ERROR){
            System.out.println(values[1]);
        }else if (code == MsgProtocol.MSG){
            System.out.println(values[1]);
        }else if (code == MsgProtocol.REMIND){
            int res= JOptionPane.showConfirmDialog(null, "Do you want to see", "Get Message", JOptionPane.YES_NO_OPTION);
            if(res==JOptionPane.YES_OPTION){
                send(MsgProtocol.MSG_NEXT + MsgProtocol.CRLF + values[1]);    //点击“是”后执行这个代码块
            }else{
                //System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
            }
        }else {
            System.out.println("Something wrong!");
        }

    }
}
