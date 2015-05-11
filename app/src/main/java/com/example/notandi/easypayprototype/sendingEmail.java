package com.example.notandi.easypayprototype;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendingEmail {
    public static void main(String[] args) {
        //Testing the sendEmail function.
        String To = "easypayuser1@gmail.com";
        String Text = "Hello World!";
        sendEmail(To,Text);
    }

    public static void sendEmail (String To, String Text) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authentication for the email we are sending from.
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("easypayuser1@gmail.com","tannbursti");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            //Who is sending the email
            message.setFrom(new InternetAddress("easypayuser1@gmail.com"));
            //Who we are sending the email to.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To));
            //Subject for the email
            message.setSubject("Testing Subject");
            //Text in the email
            message.setText(Text);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
