package edu.hm.cs.se.activitymeter.controller.email;

import edu.hm.cs.se.activitymeter.model.Activity;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

public class EmailController {

    public String[] VALIDEMAILS = {"@calpoly.edu","@hm.edu"};

    @Value("${email.name}")
    private String GMAILUSER;

    @Value("${email.password}")
    private String GMAILUPASS;

    @Value("${host.url}")
    private String host;

    private static final String SUBJECT = "Aktivity-Meter activation key";
    public static final String TEXT = "Dear User, %s to aktivate your post on the aktivity-meter klick the folowing link: %n"
            + "%s/activation/%s?key=%s";

    public boolean sendEmail(Activity activity, String aktivCode){
        if(Arrays.stream(VALIDEMAILS).anyMatch(activity.getEmail()::endsWith)){
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
                        InternetAddress.parse(activity.getEmail()));
                message.setSubject(SUBJECT);
                message.setText(String.format(TEXT, activity.getAuthor(), host, activity.getId(), aktivCode));

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            return true;
        }

        return false;
    }


    public String generateKey(){
        String result = UUID.randomUUID().toString();
        result = result.replace("-","");
        return result;
    }
}

