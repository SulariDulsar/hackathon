package com.qa.support;

import com.qa.util.Configuration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;

public class SendEmail {
    private static SendEmail self;
    private String userName;
    private String mailPass;

    private SendEmail() {
        userName = Configuration.getSafeString("userName");
        mailPass = Configuration.getSafeString("mailPass");
    }

    public static SendEmail getInstance() {
        if (self == null) {
            self = new SendEmail();
        }

        return self;
    }


    public void sendEmailWithAttachments() {
        try {
            // sets SMTP server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.user", userName);
            properties.put("mail.password", mailPass);

            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, mailPass);
                }
            };

            Session session = Session.getInstance(properties, auth);

            // creates a new e-mail message
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(userName));
            String[] emails = Configuration.getSafeString("receiverEmail").split(",");
            List<String> emailList = Arrays.asList(emails);

            InternetAddress[] toAddresses = new InternetAddress[emailList.size()];
            for (int i = 0; i < emailList.size(); i++) {
                System.out.println("Recipient[" + i + "]  " +emailList.get(i));
                toAddresses[i] = new InternetAddress(emailList.get(i));
            }

            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject("Hackathon Automation Result");
            msg.setSentDate(new Date());

            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<h3 style='color:green;'> Please refer the attachment </h3>", "text/html");

            // creates multi-part
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // adds attachments
            MimeBodyPart attachPart = new MimeBodyPart();

            try {
                attachPart.attachFile(Report.getInstance().getReportPath());

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            multipart.addBodyPart(attachPart);

            // sets the multi-part as e-mail's content
            msg.setContent(multipart);

            // sends the e-mail
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }
