package example;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Heavenkenyn on 2017/3/11.
 */

public class JavaEmail {

    final String emailAccount = "matianlong@tianyancha.com";
    final String emailSMTPHost = "smtp.exmail.qq.com";
    private Session session;

    public JavaEmail(){
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", emailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        session = Session.getDefaultInstance(props);
    }

    public int sendEmail(String receiveMail, String content){
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(emailAccount));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail));
            // 4. Subject: 邮件主题
            message.setSubject("蜉蝣验证码", "UTF-8");
            // 5. Content: 邮件正文（可以使用html标签）
            message.setContent(content, "text/html;charset=UTF-8");
            // 6. 设置发件时间
            message.setSentDate(new Date());

            Transport transport = session.getTransport();
            transport.connect(emailAccount,"East360240");
            transport.sendMessage(message,message.getAllRecipients());
            System.out.println("Send success!");
            return 1;
        } catch (MessagingException e) {
            return 0;
        }
    }

}
