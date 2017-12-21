package com.example.msgServer;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Heavenkenyn on 2017/7/29.
 */

public class MessageClient {
    private static Scanner scanner;
    public static void main(String[] args){

        TCPSocket tcpSocket = new TCPSocket();
        System.out.println("Please input commandFactory");
        while (true){
            scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            System.out.println(msg);
            tcpSocket.send(msg);
        }

    }

    private static class TCPSocket {
        private PrintStream printStream;

        public TCPSocket(){
            new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
					/* 连接服务器 */
                        Socket socket = new Socket("127.0.0.1", 9801);
				    /* 获取输出流 */
                        printStream = new PrintStream(socket.getOutputStream(), true, "utf-8");
                        InputStream inputStream = socket.getInputStream();
                        byte buffer[] = new byte[2048];
                        int temp = 0;
                        // 从InputStream当中读取客户端所发送的数据
                        while ((temp = inputStream.read(buffer)) != -1) {
                            String message = new String(buffer, 0, temp);
                            if (message!=null&&message.length()>0){
                                System.out.println(message);
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
    }
}
