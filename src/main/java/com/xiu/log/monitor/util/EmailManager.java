package com.xiu.log.monitor.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @Author: Mr.xiu
 * @Date: 2019/8/31 14:42
 * @Description: 邮件管理器
 */
@Slf4j
public class EmailManager {
    private static Session session = null;
    private final static String RESOURCES = "config/mail";
    private final static String PROTOCOL = PropertiesUtil.getValue(RESOURCES, "mail.transport.protocol");
    private final static String HOST = PropertiesUtil.getValue(RESOURCES, "mail.smtp.host");
    private final static String PORT = PropertiesUtil.getValue(RESOURCES, "mail.smtp.port");
    private final static String USER_NAME = PropertiesUtil.getValue(RESOURCES, "mail.smtp.username");
    private final static String SIGNATURE = PropertiesUtil.getValue(RESOURCES, "mail.smtp.signature");
    private static String PASSWORD = PropertiesUtil.getValue(RESOURCES, "mail.smtp.password");
    private final static boolean IS_DEBUG = PropertiesUtil.getBoolean(RESOURCES, "mail.session.debug");
    private final static boolean IS_AUTH = PropertiesUtil.getBoolean(RESOURCES, "mail.smtp.auth");
    private final static boolean IS_PWD_ENCRYPTED = PropertiesUtil.getBoolean(RESOURCES, "mail.password.encrypted");
    private final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    static {
        Properties props = new Properties(); //先声明一个配置文件以便存储信息
        props.put("mail.transport.protocol", PROTOCOL); //首先说明邮件的传输协议
        props.put("mail.smtp.host", HOST); //说明发送邮件的主机地址
        props.put("mail.smpt.port", PORT); //说明邮件发送的端口号
        props.put("mail.smtp.auth", IS_AUTH); //说明发送邮件是否需要验证，表示自己的主机发送是需要验证的
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", PORT);

        if (IS_PWD_ENCRYPTED) {
            try {
                PASSWORD = EncryptionUtil.decrypt(PASSWORD);
            } catch (Exception e) {
                log.error("fail to decrypt pwd", e);
            }
        }

        //session认证 getInstance()
        session = Session.getInstance(props, new Authenticator() {
        	@Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        });
        //这个是跟踪后台消息。打印在控制台
        session.setDebug(IS_DEBUG);
    }
    /**
     * 发送邮件
     * @param to 收件人, 多个Email以英文逗号分隔
     * @param cc 抄送, 多个Email以英文逗号分隔
     * @param subject 主题
     * @param htmlContent 内容
     * @param attachments 附件列表
     * @return
     */
    public static boolean send(String to,String cc, String subject, String htmlContent, List<File> attachments) {
        boolean suc = false;
        Transport transport = null;
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(USER_NAME, SIGNATURE));
            // 设置收件人
            if (to != null && to.length() > 0) {
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            }
            // 设置抄送人
            if (cc != null && cc.length() > 0) {
                msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            }
            msg.setSentDate(new Date());
            //中文主题设置编码
            subject = new String(Base64.encodeBase64String(subject.getBytes("GB2312")));
            msg.setSubject("=?GB2312?B?" + subject + "?=");

            Multipart multipart = new MimeMultipart();

            //内容体
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(htmlContent, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);

            //附件
            if (attachments != null && attachments.size() > 0) {
                for (File attachment : attachments) {
                    if (attachment != null && attachment.exists() && attachment.isFile()) {
                        BodyPart attachmentBodyPart = new MimeBodyPart();
                        DataSource source = new FileDataSource(attachment);
                        attachmentBodyPart.setDataHandler(new DataHandler(source));
                        attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                        multipart.addBodyPart(attachmentBodyPart);
                    }
                }
            }

            msg.setContent(multipart);
            transport = session.getTransport(PROTOCOL);
            transport.connect(HOST, USER_NAME, PASSWORD);
            transport.send(msg);
            suc = true;
        } catch (Exception e) {
            log.error("send email fail", e);
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    log.error("transport close fail", e);
                }
            }
        }
        return suc;
    }
}
