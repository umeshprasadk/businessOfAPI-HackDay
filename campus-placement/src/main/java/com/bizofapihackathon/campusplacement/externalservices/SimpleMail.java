//package com.bizofapihackathon.campusplacement.externalservices;
//
//import javax.mail.*;
//import javax.mail.Authenticator;
//import javax.mail.PasswordAuthentication;
//import javax.mail.internet.*;
//import java.util.Properties;
//
//public class SimpleMail {
//
//    private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";
//    private static final String SMTP_AUTH_USER = "myusername";
//    private static final String SMTP_AUTH_PWD = "mypwd";
//
//    public static void main(String[] args) throws Exception {
//        new SimpleMail().test();
//    }
//
//    public void test() throws Exception {
//        Properties props = new Properties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.host", SMTP_HOST_NAME);
//        props.put("mail.smtp.port", 587);
//        props.put("mail.smtp.auth", "true");
//
//        Authenticator auth = new SMTPAuthenticator();
//        Session mailSession = Session.getDefaultInstance(props, auth);
//        // uncomment for debugging infos to stdout
//        // mailSession.setDebug(true);
//        Transport transport = mailSession.getTransport();
//
//        MimeMessage message = new MimeMessage(mailSession);
//
//        Multipart multipart = new MimeMultipart("alternative");
//
//        BodyPart part1 = new MimeBodyPart();
//        part1.setText("This is multipart mail and u read part1......");
//
//        BodyPart part2 = new MimeBodyPart();
//        part2.setContent("<b>This is multipart mail and u read part2......</b>", "text/html");
//
//        multipart.addBodyPart(part1);
//        multipart.addBodyPart(part2);
//
//        message.setContent(multipart);
//        message.setFrom(new InternetAddress("me@myhost.com"));
//        message.setSubject("This is the subject");
//        message.addRecipient(Message.RecipientType.TO,
//                new InternetAddress("someone@somewhere.com"));
//
//        transport.connect();
//        transport.sendMessage(message,
//                message.getRecipients(Message.RecipientType.TO));
//        transport.close();
//    }
//
//    private class SMTPAuthenticator extends javax.mail.Authenticator {
//        public PasswordAuthentication getPasswordAuthentication() {
//            String username = SMTP_AUTH_USER;
//            String password = SMTP_AUTH_PWD;
//            return new PasswordAuthentication(username, password);
//        }
//    }
//}
