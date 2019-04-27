package com.isoftstone.upala.assets.utils;

import lombok.extern.log4j.Log4j2;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/*****************************
 *  @author 王鹏
 *  @version 2019/2/17 11:38
 *  @package com.isoftstone.upala.assets.utils
 *  @project assets
 *  @describe
 *****************************/

@Log4j2
public class EmailCard
{

    /**
     * 发送邮件方法
     */
    public static boolean sendMail (String toUser, String content)
    {

        final Properties proper = new Properties();
        proper.setProperty("mail.transport.protocol", "SMTP");
        proper.setProperty("mail.smtp.host", "smtp.163.com");
        proper.setProperty("mail.smtp.port", "25");
        proper.setProperty("mail.smtp.auth", "true");
        proper.setProperty("mail.smtp.timeout", "1000");

        //1、获取连接，连接到收件人信息
        Session session = Session.getInstance(proper, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("upala178421@163.com", "wp940818");
            }
        });

        //2、创建邮件对象
        Message message = new MimeMessage(session);
        try
        {
            message.setFrom(new InternetAddress("upala178421@163.com"));//设置发件人的信息
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toUser));//设置收件人信息
            message.setContent(content,"text/html;charset=utf-8");
        } catch (MessagingException e)
        {
            log.error("创建邮件对象错误", e);
        }

        //3、发送邮件
        try
        {
            Transport.send(message);
            return true;
        } catch (MessagingException e)
        {
            log.error("发送邮件错误！", e);
            return false;
        }
    }

}
