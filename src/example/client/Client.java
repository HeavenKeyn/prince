package com.example.client;

import com.example.MsgProtocol;

import java.util.Scanner;

/**
 * Created by Heavenkenyn on 2017/4/16.
 */

public class Client {
    private static Scanner scanner;
    public static void main(String[] args){

        TCPSocket tcpSocket = new TCPSocket();
        System.out.println("Please input commandFactory");
        while (true){
            scanner = new Scanner(System.in);
            int command = scanner.nextInt();
            String msg = commandFactory(command);
            System.out.println(msg);
            tcpSocket.send(msg);
        }

        /*File file = new File(MsgProtocol.FILE_PATH);
        if (file.exists()){
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line = null;
                while ((line = bufferedReader.readLine())!=null){
                    System.out.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/

    }

    private static String commandFactory(int command){
        MsgProtocol.CommandBuilder cmdBuilder = new MsgProtocol.CommandBuilder();
        cmdBuilder.append(command);
        if (command==MsgProtocol.LOGIN){
            System.out.println("Please input username");
            cmdBuilder.append(scanner.next());
            System.out.println("Please input password");
            cmdBuilder.append(scanner.next());
        }else if (command==MsgProtocol.LOGOUT){
            System.out.println("Please input username");
            cmdBuilder.append(scanner.next());
        }else if (command == MsgProtocol.REGISTER){
            System.out.println("Please input username");
            cmdBuilder.append(scanner.next());
            System.out.println("Please input password");
            cmdBuilder.append(scanner.next());
            System.out.println("Please input birthday");
            cmdBuilder.append(scanner.next());
            System.out.println("Please input telephone");
            cmdBuilder.append(scanner.next());
            System.out.println("Please input address");
            cmdBuilder.append(scanner.next());
        }else if (command == MsgProtocol.UPDATE){
            System.out.println("Please input username");
            cmdBuilder.append(scanner.next());
            System.out.println("Please input the attribute name want to update");
            cmdBuilder.append(scanner.next());
            System.out.println("Please input the attribute value want to update");
            cmdBuilder.append(scanner.next());
        } else if (command == MsgProtocol.SEND){
            System.out.println("Please input username");
            cmdBuilder.append(scanner.next());
            System.out.println("Please input the user want to send");
            cmdBuilder.append(scanner.next());
            System.out.println("Please input content");
            cmdBuilder.append(scanner.next());
        }else if (command == MsgProtocol.MSG_WAIT){
            System.out.println("Please input username");
            cmdBuilder.append(scanner.next());
        }else if (command == MsgProtocol.MSG_NEXT){
            System.out.println("Please input username");
            cmdBuilder.append(scanner.next());
        }else if (command == MsgProtocol.MSG_ALL){
            System.out.println("Please input username");
            cmdBuilder.append(scanner.next());
        }
        return cmdBuilder.toString();
    }
}
