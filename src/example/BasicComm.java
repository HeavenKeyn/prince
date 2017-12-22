package example;

import example.values.SocketComm;

/**
 * Created by Heavenkenyn on 2017/3/16.
 */

public class BasicComm {

    Connection connection;

    public BasicComm(Connection connection){
        this.connection = connection;
    }

    public void responseFor(String message){
        String[] values = message.split(";",2);
        int code;
        try {
            code = Integer.parseInt(values[0]);
        }catch (NumberFormatException e){
            code = SocketComm.UNIDENTIFIED;
        }
        switch (code){

        }
    }

    public interface Connection{

    }
}
