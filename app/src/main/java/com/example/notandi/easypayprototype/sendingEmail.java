package com.example.notandi.easypayprototype;
/*
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.MediaType;
*/
//import org.glassfish.jersey.client.Client;


public class sendingEmail {
    /*
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(To));
            //Subject for the email
            message.setSubject("Testing Subject");
            //Text in the email
            message.setText(Text);
            SendSimpleMessage();
            Transport.send(message);

            System.out.println("Done");

        }
        catch (MessagingException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static ClientResponse SendSimpleMessage() {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api",
                "key-cfb08d9fe9ffb6396e50f728797fe4a0"));
        WebResource webResource =
                client.resource("https://api.mailgun.net/v3/sandbox1bf7f51accba4dda89b382ad26a44fa2.mailgun.org/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Mailgun Sandbox <postmaster@sandbox1bf7f51accba4dda89b382ad26a44fa2.mailgun.org>");
        formData.add("to", "kristofer <kristofer1985@gmail.com>");
        formData.add("subject", "Hello kristofer");
        formData.add("text", "Congratulations kristofer, you just sent an email with Mailgun!  You are truly awesome!  You can see a record of this email in your logs: https://mailgun.com/cp/log .  You can send up to 300 emails/day from this sandbox server.  Next, you should add your own domain so you can send 10,000 emails/month for free.");
        return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
                post(ClientResponse.class, formData);
    }
    */

}
