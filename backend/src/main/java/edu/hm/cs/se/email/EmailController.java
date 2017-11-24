package edu.hm.cs.se.email;

import java.util.List;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailController {
    public static String[] VALIDEMAILS = {"calpoly.edu","hm.edu"};
    public static String GMAILUSER = "activity.meterhm@gmail.com";
    public static String GMAILUPASS = "holzwurm3";
    public static String SUBJECT = "Aktivity-Meter activation key";
    public static String TEXT = "Dear User, \n" +
                                "to aktivate your post on the aktivity-meter klick the folowing link:";

    public static boolean sendEmail(String adress,String aktivCode){
        String to = "leon.lukas11@web.de";


        if(adress.split("@")[1] == VALIDEMAILS[0]||adress.split("@")[1] == VALIDEMAILS[1]){
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
                        InternetAddress.parse(to));
                message.setSubject(SUBJECT);
                message.setText(TEXT);

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

