package example;

import example.model.user.PersonInfo;
import example.model.user.RegisterInfo;
import example.model.user.PersonUser;
import example.values.SocketComm;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Heavenkenyn on 2017/3/17.
 */

public class BasicServer {
    ServerSocket serverSocket = null;

    public BasicServer(){
        try {
            // 创建一个ServerSocket对象，并让这个Socket在1989端口监听
            serverSocket = new ServerSocket(1986);
            System.out.println("Create BasicServer");
            // 调用ServerSocket的accept()方法，接受客户端所发送的请求，
            // 如果客户端没有发送数据，那么该线程就停滞不继续
            while (true) {
                System.out.println("__________");
                Socket socket = serverSocket.accept();
                new BasicThread(socket).start();
            }
            //serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class BasicThread extends Thread{
        Socket socket;
        String sign;
        String state;

        public BasicThread(Socket socket) {
            this.socket = socket;
            this.sign = socket.getRemoteSocketAddress().toString();
        }

        public void run() {
            System.out.println(sign+": " + new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date()));
            System.out.println(sign+": " + "socket.getRemoteSocketAddress:" + socket.getRemoteSocketAddress());
            System.out.println(sign+": " + "socket.getLocalPort:" + socket.getLocalPort());
            System.out.println(sign+": " + "socket.getLocalAddress:" + socket.getLocalAddress());
            // 从Socket当中得到InputStream对象
            InputStream inputStream = null;
            try {
                inputStream = socket.getInputStream();
                PrintStream output = new PrintStream(socket.getOutputStream(), true, "utf-8");
                PrintWriter pw = new PrintWriter(socket.getOutputStream());

                System.out.println(sign+": " + "socket.getInputStream!");
                byte buffer[] = new byte[SocketComm.INTENT+SocketComm.CONTENT];
                int temp = 0;
                // 从InputStream当中读取客户端所发送的数据
                while ((temp = inputStream.read(buffer)) != -1) {
                    String message = new String(buffer, 0, temp);
                    String response = responseFor(message);
                    pw.println(response);
                    pw.flush();
                }
                System.out.println(sign+": " + "socket.close!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String responseFor(String message){
            String response = null;
            System.out.println(sign+": " + message);
            String[] values = message.split(";",2);
            int code;
            try {
                code = Integer.parseInt(values[0]);
            }catch (NumberFormatException e){
                code = SocketComm.UNIDENTIFIED;
            }
            switch (code){
                case SocketComm.CONNECT:
                    response = ""+SocketComm.CONNECT;
                    System.out.println(sign+": " + response);
                    break;
                case SocketComm.USER_REGISTER:
                    PersonUser user = new PersonUser(RandomUtil.getRandomString(8));
                    RegisterInfo registerInfo = RegisterInfo.decode(values[1]);
                    if (state.equals(registerInfo.getEmail()+registerInfo.getPIN())){
                        user.setRegisterInfo(registerInfo);
                        System.out.println(sign+": " + user.code());
                        MySQL.getInstance(MySQL.DB_USER).insert(user);
                        response = code+SocketComm.SUCCESS+";"+user.code();
                    }else {
                        response = code+SocketComm.ERROR+"";
                    }
                    break;
                case SocketComm.USER_REGISTER+SocketComm.PIN:
                    String receiver = values[1];
                    String PIN = RandomUtil.getRandomString(4);
                    int result = new JavaEmail().sendEmail(receiver,PIN);
                    if(result == 1){
                        state = receiver+PIN;
                        result = SocketComm.SUCCESS;
                    }
                    System.out.println(sign+": " + state);
                    response = SocketComm.SEND_EMAIL +result + "";
                    System.out.println(sign+": " + response);
                    break;
                case SocketComm.USER_SIGN_IN:
                    PersonInfo personInfo = PersonInfo.decode(values[1]);
                    PersonUser personUser = MySQL.getInstance(MySQL.DB_USER).load(null,personInfo.getEmail());
                    if (personInfo.getPassword().equals(personUser.getPassword()))
                        response = code+SocketComm.SUCCESS+";"+personUser.code();
                    else
                        response = code+SocketComm.ERROR+"";
                    System.out.println(sign+": " +"SIGN_IN: "+ response);
                    break;
                default:
            }

            System.out.println(sign+": " + "finish response!");
            return response;
        }

    }
}
