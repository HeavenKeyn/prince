package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import server.factory.Entrance;
import server.log.Log;
import server.log.LogFactory;
import server.model.Message;

public class ClientThread extends Thread{
	private final String CLR = ";;;";
	private Socket socket;
    String sign;
    private PrintWriter output;
    public int uid;
    public String vcode;
    private Log log = LogFactory.getLog(getClass());

    public ClientThread(Socket socket) {
        this.socket = socket;
        this.sign = socket.getRemoteSocketAddress().toString();
    }
    
    public ClientThread() {
    	
    }

    public void run() {
    	log.info(sign);
        System.out.println(sign+": " + new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date()));
        System.out.println(sign+": " + "socket.getRemoteSocketAddress:" + socket.getRemoteSocketAddress());
        System.out.println(sign+": " + "socket.getLocalPort:" + socket.getLocalPort());
        System.out.println(sign+": " + "socket.getLocalAddress:" + socket.getLocalAddress());
        try {
        	InputStream inputStream = socket.getInputStream();
            //PrintStream output = new PrintStream(socket.getOutputStream(), true, "utf-8");
            output = new PrintWriter(socket.getOutputStream());

            System.out.println(sign+": " + "socket.getInputStream!");
            byte buffer[] = new byte[1024];
            int temp = 0;
            while ((temp = inputStream.read(buffer)) != -1) {
                String message = new String(buffer, 0, temp);
                receive(message);
            }
            System.out.println(sign+": " + "socket.close!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void send(Message message) {
		if (output!=null) {
			output.println(message.toJson()+CLR);
		}
	}
    
    private void receive(String str) {
    	if (str!=null) {
			String strs[] = str.split(CLR);
			for (String string : strs) {
				Message message = new Entrance(Message.fromJson(string),this).getFactory().getResponse();
				if (message!=null) {
					send(message);
				}
			}
		}
    }


}
