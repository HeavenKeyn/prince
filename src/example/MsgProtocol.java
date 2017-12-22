package example;

/**
 * Created by Heavenkenyn on 2017/4/15.
 */

public class MsgProtocol {

    public static final int LOGIN = 101;
    public static final int LOGOUT = 102;
    public static final int SEND = 103;
    public static final int MSG_WAIT = 104;
    public static final int MSG_NEXT = 105;
    public static final int MSG_ALL = 106;
    public static final int REGISTER = 107;
    public static final int UPDATE = 108;
    public static final int SUCCESS = 200;
    public static final int MSG = 201;
    public static final int REMIND = 202;
    public static final int ERROR = 500;
    public static final String CRLF = "\r\n";
    public static final String FILE_PATH = "h:\\pwd.xml";

    public static class CommandBuilder{
        private StringBuilder builder;

        public CommandBuilder() {
            this.builder = new StringBuilder();
        }

        public StringBuilder append(int command){
            return builder.append(command + CRLF);
        }

        public StringBuilder append(String command){
            return builder.append(command + CRLF);
        }

        @Override
        public String toString(){
            return builder.toString();
        }
    }

}
