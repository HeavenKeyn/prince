package example.msgServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by Heavenkenyn on 2017/7/19.
 */

public class UpdateCommand implements Command{
    private BufferedWriter out;
    private BufferedReader in;
    private MsgSvrConnection conn;

    public UpdateCommand(BufferedReader in, BufferedWriter out,
                           MsgSvrConnection serverConn)
    {
        this.out = out;
        this.in = in;
        this.conn = serverConn;
    }

    public void execute() throws IOException {
        String username = in.readLine();
        String key = in.readLine();
        String value = in.readLine();

        if (conn.getServer().isValidUser(username)){
            if (key.equals("password")){
                conn.getServer().setUser(username,value);
            }else {
                conn.getServer().setUser(username+key,value);
            }
            out.write("200\r\n");
            out.flush();
        }else {
            (new ErrorCommand(in, out,conn, "The username does not exist.")).execute();
        }
    }
}
