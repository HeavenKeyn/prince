package com.example;

import com.example.model.msg.Mail;
import com.example.model.msg.RecordMail;
import com.example.values.SocketComm;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Heavenkenyn on 2017/3/17.
 */

public class ChatServer {
    ServerSocket serverSocket = null;
    final String SERVER_NAME = "ChatServer";
    static Map<String,ChatThread> clients = new HashMap();

    public ChatServer(){
        try {
            serverSocket = new ServerSocket(1989);
            System.out.println("Create "+SERVER_NAME);
            while (true) {
                System.out.println("__________");
                Socket socket = serverSocket.accept();
                new ChatThread(socket).start();
            }
            //serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ChatThread extends Thread{
        Socket socket;
        String sign;
        String user;
        public PrintWriter printWriter;

        public ChatThread(Socket socket){
            this.socket = socket;
            this.sign = socket.getRemoteSocketAddress().toString();
        }

        public void run(){
            try {
                InputStream inputStream = socket.getInputStream();
                printWriter = new PrintWriter(socket.getOutputStream());
                printOut("Accept");
                byte buffer[] = new byte[4*1024];
                int temp = 0;
                // 从InputStream当中读取客户端所发送的数据
                //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // String str;
                while ((temp = inputStream.read(buffer)) != -1) {//((str=bufferedReader.readLine())!=null){
                    String message = new String(buffer, 0, temp);
                    //String message = str;
                    printOut(message);
                    if (message!=null)
                    divide(message);
                    //String response = responseFor(message);
                    //printOut(response);
                }
                clients.remove(user);
                printOut("socket.close!");
            } catch (IOException e) {
                clients.remove(user);
                printOut("remove user!");
            }
        }

        void divide(String message){
            String[] mails = message.split(SocketComm.MAIL_SPLIT);
            for (int i=0;i<mails.length;i++){
                printOut("Response: "+responseFor(mails[i]));
            }
        }

        String responseFor(String message){
            Mail mail = Mail.decode(message);
            String response = null;
            if (mail.getId().length()==0){
                user = mail.getSender();
                clients.put(user,this);
                sign = sign+"/"+user+"/";
                response = SocketComm.CONNECT+";"+user;
                send(response);
                List<Mail> list = MySQL.getInstance(MySQL.DB_USER).load(user);
                for (int i=0;i<list.size();i++)
                    this.send(list.get(i));
                printOut(user + " Connect");
            }else if (mail.getMsg()!=null){
                RecordMail recordMail = RecordMail.decode(message);

                String receiver = mail.getMessenger();

                recordMail.setReceiver(receiver);
                ChatThread client = clients.get(receiver);
                if (client != null){
                    client.send(mail);
                }else {
                    MySQL.getInstance(MySQL.DB_USER).insert(recordMail);
                }
                response = SocketComm.MESSAGE_SENT + ";" + mail.getId();//new Mail(send.getId(),send.getMessenger()).code();
                send(response);
            }else {//if (!user.equals(mail.getSender()))//yi du hui zhi
                MySQL.getInstance(MySQL.DB_USER).update(mail.getId(),mail.getSender());
                response = SocketComm.MESSAGE_READ+";"+mail.getId();
                send(response);
            }
            return response;
        }

        public void send(String message){
            printWriter.println(message);
            printWriter.flush();
        }

        private void send(Mail mail){
            send(SocketComm.MESSAGE+";"+mail.code());
        }

        void printOut(String string){
            System.out.println(SERVER_NAME+": " + sign + string);
        }

        void printOut(Object object){
            System.out.println(SERVER_NAME+": " + sign + object.toString());
        }
    }

}
