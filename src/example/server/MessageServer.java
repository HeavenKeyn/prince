package example.server;

import example.MsgData;
import example.MsgProtocol;
import example.User;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Heavenkenyn on 2017/4/16.
 */

public class MessageServer {
    private static Map<String,MsgSvrConnection> clients = new HashMap();

    public MessageServer(){
        try {
            // 鍒涘缓涓�涓猄erverSocket瀵硅薄锛屽苟璁╄繖涓猄ocket鍦�1989绔彛鐩戝惉
            ServerSocket serverSocket = new ServerSocket(1984);
            System.out.println("Create BasicServer");
            // 璋冪敤ServerSocket鐨刟ccept()鏂规硶锛屾帴鍙楀鎴风鎵�鍙戦�佺殑璇锋眰锛�
            // 濡傛灉瀹㈡埛绔病鏈夊彂閫佹暟鎹紝閭ｄ箞璇ョ嚎绋嬪氨鍋滄粸涓嶇户缁�
            while (true) {
                System.out.println("__________");
                Socket socket = serverSocket.accept();
                new MsgSvrConnection(socket).start();
            }
            //serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MsgSvrConnection extends Thread{
        private Socket socket;
        private String user;
        private List<MsgData> list = new ArrayList<>();
        private PrintStream printWriter;

        public MsgSvrConnection(Socket socket){
            this.socket = socket;
        }

        public void run(){
            try {
                InputStream inputStream = socket.getInputStream();
                printWriter = new PrintStream(socket.getOutputStream(), true, "utf-8");
                //PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

                System.out.println("socket.getInputStream!");
                byte buffer[] = new byte[2048];
                int temp = 0;
                // 浠嶪nputStream褰撲腑璇诲彇瀹㈡埛绔墍鍙戦�佺殑鏁版嵁
                while ((temp = inputStream.read(buffer)) != -1) {
                    String message = new String(buffer, 0, temp);
                    if (message!=null&&message.length()>0&&!message.equals(MsgProtocol.CRLF)){
                        System.out.println(message);
                        String response = commandFactory(message);
                        System.out.println(response);
                        send(response);
                    }

                }
                System.out.println("socket.close!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String commandFactory(String message){
            String response = null;
            String[] values = message.split(MsgProtocol.CRLF,2);
            String username = message.split(MsgProtocol.CRLF,3)[1];
            int code;
            try {
                code = Integer.parseInt(values[0]);
            }catch (NumberFormatException e){
                code = MsgProtocol.ERROR;
            }
            if (code == MsgProtocol.LOGIN){
                response = loginCommand(values[1]);
            }else if (code == MsgProtocol.REGISTER){
                response = registerCommand(values[1]);
            }else if (username.equals(user)){
                if (code == MsgProtocol.UPDATE){
                    response = updateCommand(values[1]);
                }else if (code == MsgProtocol.LOGOUT){
                    response = logoutCommand(values[1]);
                }else if (code == MsgProtocol.SEND){
                    response = sendCommand(values[1]);
                }else if (code == MsgProtocol.MSG_WAIT){
                    response = waitMsgCommand(values[1]);
                }else if (code == MsgProtocol.MSG_NEXT){
                    response = nextMsgCommand(values[1]);
                }else if (code == MsgProtocol.MSG_ALL){
                    response = allMsgCommand(values[1]);
                }
            }else {
                response = new MsgProtocol.CommandBuilder().append(MsgProtocol.ERROR).append("You have not login").toString();
            }
            return response;
        }

        private void send(String message){
            printWriter.println(message);
            printWriter.flush();
        }

        private String loginCommand(String message){
            String[] values = message.split(MsgProtocol.CRLF);
            MsgProtocol.CommandBuilder cmdBuilder = new MsgProtocol.CommandBuilder();
            XMLFactory xmlFactory = XMLFactory.getInstance(new File(MsgProtocol.FILE_PATH));
            clients.put(values[0],this);
            user = values[0];
            String password = xmlFactory.getPassword(values[0]);
            if (password.equals(values[1])){
                cmdBuilder.append(MsgProtocol.SUCCESS);
            }else {
                cmdBuilder.append(MsgProtocol.ERROR);
                cmdBuilder.append("Incorrect Password!");
            }
            return cmdBuilder.toString();
        }

        private String logoutCommand(String message){
            String[] values = message.split(MsgProtocol.CRLF);
            MsgProtocol.CommandBuilder cmdBuilder = new MsgProtocol.CommandBuilder();
            clients.remove(values[0]);
            cmdBuilder.append(MsgProtocol.SUCCESS);
            return cmdBuilder.toString();
        }

        private String registerCommand(String message){
            String[] values = message.split(MsgProtocol.CRLF);
            MsgProtocol.CommandBuilder cmdBuilder = new MsgProtocol.CommandBuilder();
            User user = new User(values[0]);
            user.setPassword(values[1]);
            user.setBirthday(values[2]);
            user.setTelephone(values[3]);
            user.setAddress(values[4]);
            XMLFactory xmlFactory = XMLFactory.getInstance(new File(MsgProtocol.FILE_PATH));
            if (xmlFactory.createElement(user)){
                xmlFactory.save();
                cmdBuilder.append(MsgProtocol.SUCCESS);
            }else {
                cmdBuilder.append(MsgProtocol.ERROR);
                cmdBuilder.append("The Name already Exists");
            }
            return cmdBuilder.toString();
        }

        private String updateCommand(String message){
            String[] values = message.split(MsgProtocol.CRLF);
            MsgProtocol.CommandBuilder cmdBuilder = new MsgProtocol.CommandBuilder();
            XMLFactory xmlFactory = XMLFactory.getInstance(new File(MsgProtocol.FILE_PATH));
            if (xmlFactory.updateElement(values[0],values[1],values[2])){
                xmlFactory.save();
                cmdBuilder.append(MsgProtocol.SUCCESS);
            }else {
                cmdBuilder.append(MsgProtocol.ERROR);
                cmdBuilder.append("The Name Does not Exist");
            }
            return cmdBuilder.toString();
        }

        private String sendCommand(String message){
            String[] values = message.split(MsgProtocol.CRLF);
            MsgProtocol.CommandBuilder cmdBuilder = new MsgProtocol.CommandBuilder();
            MsgData msgData = new MsgData();
            msgData.setUser_id(values[1]);
            msgData.setContent(values[2]);
            MsgSvrConnection client = clients.get(values[1]);
            if (client!=null){
                client.remindUser(values[1]);
            }
            MySQL.getInstance().insert(values[0],msgData);
            cmdBuilder.append(MsgProtocol.SUCCESS);
            return cmdBuilder.toString();
        }

        private String waitMsgCommand(String message){
            String[] values = message.split(MsgProtocol.CRLF);
            MsgProtocol.CommandBuilder cmdBuilder = new MsgProtocol.CommandBuilder();
            if (list.size()==0){
                list = MySQL.getInstance().load(values[0]);
            }
            cmdBuilder.append(MsgProtocol.SUCCESS);
            cmdBuilder.append("You have "+list.size()+" messages.");
            return cmdBuilder.toString();
        }

        private String nextMsgCommand(String message){
            String[] values = message.split(MsgProtocol.CRLF);
            MsgProtocol.CommandBuilder cmdBuilder = new MsgProtocol.CommandBuilder();
            MsgData msgData = MySQL.getInstance().get(values[0]);
            MySQL.getInstance().update(msgData.getId());
            cmdBuilder.append(MsgProtocol.MSG);
            cmdBuilder.append(1);
            cmdBuilder.append(msgData.code());
            return cmdBuilder.toString();
        }

        private String allMsgCommand(String message){
            String[] values = message.split(MsgProtocol.CRLF);
            MsgProtocol.CommandBuilder cmdBuilder = new MsgProtocol.CommandBuilder();
            int size = list.size();
            if (size==0){
                list = MySQL.getInstance().load(values[0]);
            }
            cmdBuilder.append(MsgProtocol.MSG);
            cmdBuilder.append(size);
            for (int i=size-1;i>-1;i--){
                MsgData msgData = list.get(i);
                cmdBuilder.append(msgData.code());
                MySQL.getInstance().update(msgData.getId());
                list.remove(i);
            }
            return cmdBuilder.toString();
        }

        public void remindUser(String user){
            MsgProtocol.CommandBuilder cmdBuilder = new MsgProtocol.CommandBuilder();
            cmdBuilder.append(MsgProtocol.REMIND);
            cmdBuilder.append(user);
            send(cmdBuilder.toString());
        }

    }
}
