package example.msgServer;
import java.io.BufferedReader;
import java.io.BufferedWriter; 
import java.io.IOException;
import java.util.List;

public class GetAllMsgsCommand implements Command 
{
  private BufferedReader in;
  private BufferedWriter out;
  private MsgSvrConnection conn;

  public GetAllMsgsCommand(BufferedReader in, BufferedWriter out, MsgSvrConnection serverConn)
  { 
    this.in = in;
    this.out = out;
    this.conn = serverConn;
  }

  public void execute() throws IOException
  {
      //declare a variable user of type String and use it to get the user from the inputstream
      String user = in.readLine();
      //check if current user is not equal to null and current user is equal to the user (use the method getCurrentUser())
      if (conn.getCurrentUser() != null &&
              conn.getCurrentUser().equals(user)) {
          //intialise an array (msgs) that is used to hold all the messages read and set it's initialised value to null
          //Message[] m = null;
          //use the method getAllMessages(user) to populate the msgs array
          //check if msgs is not equal to null
          //if ((m = conn.getServer().getMessages().getAllMessages(user)) != null)
          List<Message> m = new GetMsgList("msg").excute();
          if (m != null)
          {
              //write to the OutputStream the message status code as specified in the protocol
              out.write("" + MsgProtocol.MESSAGE + "\r\n");
              //write the length of the messages returned
              out.write("" + m.size() + "\r\n");
              //Loop through the messages and write the sender, date and cotent to the outputstream (use provided methods)
              for (int i=0;i<m.size();i++){
                  out.write(m.get(i).getSender() + "\r\n");
                  out.write(m.get(i).getDate() + "\r\n");
                  out.write(m.get(i).getContent() + "\r\n");
              }
              //Flush the outputstream
              out.flush();
          } else        //capture adequet errors (No messages) or (You are not logged on)
          {
              (new ErrorCommand(in, out,conn, "No messages")).execute();
          }
      } else
      {
          (new ErrorCommand(in, out,conn, "You are not logged on")).execute();
      }
          
}
}
