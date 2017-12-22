package example;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Heavenkenyn on 2017/4/17.
 */

public class MsgData {
    private String id;
    private String user_id;
    private String date_send;
    private String content;

    public MsgData(){
        this.id = getRandomString(4);
        this.date_send = new SimpleDateFormat("yyyyMMMdd E HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTimeInMillis());
    }

    public MsgData(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate_send() {
        return date_send;
    }

    public void setDate_send(String date_send) {
        this.date_send = date_send;
    }

    public String code(){
        return new Gson().toJson(this);
    }

    public static MsgData decode(String jsonStr){
        try {
            return new Gson().fromJson(jsonStr,MsgData.class);
        }catch (JsonSyntaxException e){
            return null;
        }
    }

    private String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
