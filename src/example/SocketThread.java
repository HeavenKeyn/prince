package example;

import example.model.user.RegisterInfo;
import example.model.user.PersonUser;
import example.values.SocketComm;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Heavenkenyn on 2017/2/13.
 */

public class SocketThread extends Thread{
    Socket socket;
    String sign;
    String state;

    public SocketThread(Socket socket) {
        this.socket = socket;
        this.sign = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
    }

    public void run() {
        System.out.println(sign+": " + "socket.getInetAddress:" + socket.getInetAddress());
        System.out.println(sign+": " + "socket.getRemoteSocketAddress:" + socket.getRemoteSocketAddress());
        System.out.println(sign+": " + "socket.getPort:" + socket.getPort());
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
            }/**/
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
                    response = ""+code+SocketComm.SUCCESS+";"+user.code();
                }else {
                    response = ""+code+SocketComm.ERROR;
                }
                //user.setEmail(values[1]);
                //MySQL.getInstance(MySQL.DB_USER).insert(user);
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
                response ="" + SocketComm.SEND_EMAIL +result;
                break;
            default:
        }

        System.out.println(sign+": " + "finish response!");
        return response;
    }

}
