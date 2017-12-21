package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;


public class HKServer {
	private ServerSocket serverSocket = null;
	private final int PORT = 1986;
	public static Hashtable<String, ClientThread> hashtable = new Hashtable<>();;
	
	public HKServer(){
        
    }
	
	public void start() {
		try {
            // 閸掓稑缂撴稉锟芥稉鐚別rverSocket鐎电钖勯敍灞借嫙鐠佲晞绻栨稉鐚刼cket閸︼拷1989缁旑垰褰涢惄鎴濇儔
            serverSocket = new ServerSocket(PORT);
            System.out.println("Create BasicServer");
            // 鐠嬪啰鏁erverSocket閻ㄥ垷ccept()閺傝纭堕敍灞惧复閸欐顓归幋椋庮伂閹碉拷閸欐垿锟戒胶娈戠拠閿嬬湴閿涳拷
            // 婵″倹鐏夌�广垺鍩涚粩顖涚梾閺堝褰傞柅浣规殶閹诡噯绱濋柇锝勭疄鐠囥儳鍤庣粙瀣皑閸嬫粍绮告稉宥囨埛缂侊拷
            while (true) {
                System.out.println("__________");
                Socket socket = serverSocket.accept();
                new ClientThread(socket).start();
            }
            //serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
