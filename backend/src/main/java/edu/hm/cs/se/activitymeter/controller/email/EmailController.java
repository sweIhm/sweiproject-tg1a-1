package edu.hm.cs.se.activitymeter.controller.email;

import edu.hm.cs.se.activitymeter.model.Comment;
import edu.hm.cs.se.activitymeter.model.Post;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class EmailController {
  private static final String[] VALIDEMAILS = {"calpoly.edu","hm.edu"};
  private static final String SUBJECT = "Your activity on Activitymeter";
  private static final String TEXT = "Hello %s! \nThank you for your submission! Your activity "
      + "will appear on Activitymeter as soon as you authenticate yourself as a member of the "
      + "California Polytechnic State University or the Munich University of Applied Sciences "
      + "by clicking the link below: %n%s/activation/%s?key=%s";

  private static final String TEXTCOMMENT = "Hello %s!\nThank you for your submission! "
          + "Your comment will appear on Activitymeter as soon as you authenticate yourself as a "
          + "member of the California Polytechnic State University or the Munich University of "
          + "Applied Sciences by clicking the link below: %n%s/activation/%s?key=%s";

  private static final String SUBJECTCOMMENT = "Your comment on Activitymeter";

  private static final String TEXTNOTE = "Hello %s! \nyour Activity %s has been commented!";

  private static final String SUBJECTNOTE = "%s has been commented";

  @Value("${email.name}")
  private String gmailuser;

  @Value("${email.password}")
  private String gmailupass;

  @Value("${host.url}")
  private String host;

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  /**
   * Sends activation mail for a post.
   * @param post Post to be activated
   * @param activationKey Activation key
   * @return if email was send successfully
   */
  public boolean sendEmail(Post post, String activationKey) {

    if (Arrays.stream(VALIDEMAILS).anyMatch(post.getEmail()::endsWith)) {

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");

      Session session = Session.getInstance(props,
          new GMailAuthenticator(gmailuser, gmailupass));

      log.info("Try sending email to " + post.getEmail());
      try {
        Message message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(post.getEmail()));
        message.setSubject(SUBJECT);
        message.setText(String.format(TEXT, post.getAuthor(), host, post.getId(), activationKey));

        Transport.send(message);
        log.info("Email send successful!");
        return true;
      } catch (MessagingException e) {
        log.error(e.toString());
      }
    }
    return false;
  }

  /**
   * Sends activation mail for a comment.
   * @param comment Comment to be activated
   * @param activationKey Activation key
   * @return if email was send successfully
   */
  public boolean sendEmail(Comment comment, String activationKey) {
    if (Arrays.stream(VALIDEMAILS).anyMatch(comment.getEmail()::endsWith)) {

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");

      Session session = Session.getInstance(props,
              new GMailAuthenticator(gmailuser, gmailupass));

      log.info("Try sending email to " + comment.getEmail());
      try {
        Message message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(comment.getEmail()));
        message.setSubject(SUBJECTCOMMENT);
        message.setText(String.format(TEXTCOMMENT, comment.getAuthor(),
                host, comment.getId(), activationKey));

        Transport.send(message);
        return true;
      } catch (MessagingException e) {
        log.error(e.toString());
      }
    }
    return false;

  }

  /**
   * Sends notification mail about comment.
   * @param post Post which was commented on
   * @return if email was send successfully
   */
  public boolean sendNotification(Post post) {

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new GMailAuthenticator(gmailuser, gmailupass));

    log.info("Try sending email to " + post.getEmail());
    try {
      Message message = new MimeMessage(session);
      message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse(post.getEmail()));
      message.setSubject(String.format(SUBJECTNOTE,post.getTitle()));
      message.setText(String.format(TEXTNOTE, post.getAuthor(),post.getTitle()));

      Transport.send(message);
      log.info("Email send successful!");
      return true;
    } catch (MessagingException e) {
      log.error(e.toString());
    }
    return false;
  }

  /**
   * Generates activation key.
   * @return activation key
   */
  public String generateKey() {
    String result = UUID.randomUUID().toString();
    result = result.replace("-","");
    return result;
  }
}

