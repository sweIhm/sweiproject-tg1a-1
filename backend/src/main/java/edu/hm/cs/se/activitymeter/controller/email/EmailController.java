package edu.hm.cs.se.activitymeter.controller.email;

import edu.hm.cs.se.activitymeter.model.Post;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailController {
    public static String[] VALIDEMAILS = {"calpoly.edu","hm.edu"};
    public static String SUBJECT = "Aktivity-Meter activation key";
    public static String TEXT = "Dear User, %s to aktivate your post on the aktivity-meter klick the folowing link: %n"
                                   + "%s/activation/%s?key=%s";

    @Value("${email.name}")
    private  String GMAILUSER;

    @Value("${email.password}")
    private String GMAILUPASS;

    @Value("${host.url}")
    private  String host;

    public  boolean sendEmail(Post post, String aktivCode){


        if(Arrays.stream(VALIDEMAILS).anyMatch(post.getEmail()::endsWith)){

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new GMailAuthenticator(GMAILUSER, GMAILUPASS));

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("noreply@aktivity-meter.edu"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(post.getEmail()));
                message.setSubject(SUBJECT);
                message.setText(String.format(TEXT, post.getAuthor(), host, post.getId(), aktivCode));

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            return true;
        }

        return false;


    }


    public static String generateKey(){
        String result = UUID.randomUUID().toString();
        result = result.replace("-","");
        return result;
    }
}

