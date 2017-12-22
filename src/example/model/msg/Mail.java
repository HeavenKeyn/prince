package example.model.msg;

import example.RandomUtil;
import example.model.msg.Msg;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Calendar;

/**
 * Created by Heavenkenyn on 2017/3/23.
 */

public class Mail {
    protected String id;
    protected String sender;
    protected String messenger;
    protected Msg msg;
    protected long createTime;

    protected Mail(){
        this.id = RandomUtil.getRandomString(16);
        this.createTime = Calendar.getInstance().getTimeInMillis();
    }

    public Mail(String id){
        this.id = id;
    }

    public Mail(Msg msg){
        this();
        this.msg = msg;
    }

    public Mail(String id,String sender) {
        this.id = id;
        this.sender = sender;
    }

    public Mail(String sender, Msg msg) {
        this(msg);
        this.sender = sender;
    }

    public Mail(String sender, Msg msg, String messenger){
        this(sender,msg);
        this.messenger = messenger;
    }

    public Mail(String id,String sender, Msg msg, String messenger){
        this(sender, msg, messenger);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        if (msg != null)
            this.msg = msg;
    }

    public String code(){
        return new Gson().toJson(this);
    }

    public static Mail decode(String jsonStr){
        try {
            return new Gson().fromJson(jsonStr,Mail.class);
        }catch (JsonSyntaxException e){
            return null;
        }
    }

    /*public static Mail decodeThrows(String jsonStr) throws JsonSyntaxException{
        return new Gson().fromJson(jsonStr,Mail.class);
    }*/

    /*@Override
    public Mail clone(){
        Mail mail = null;
        try {
            mail = (Mail) super.clone();
        } catch (CloneNotSupportedException e) {
            return mail;
        }
        return mail;
    }*/

}
