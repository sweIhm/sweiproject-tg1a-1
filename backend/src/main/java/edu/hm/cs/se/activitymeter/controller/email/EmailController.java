package edu.hm.cs.se.activitymeter.controller.email;

import edu.hm.cs.se.activitymeter.model.Comment;
import edu.hm.cs.se.activitymeter.model.Post;
import java.util.Arrays;
import java.util.List;
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
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"test", "prod"})
@Primary
public class EmailController {

  private static final String ACTIVITY_ACTIVATION_SUBJECT = "Your activity on Activitymeter";
  private static final String ACTIVITY_ACTIVATION_TEXT = "Hello %s!%nThank you for your "
      + "submission! Your activity will appear on Activitymeter as soon as you authenticate "
      + "yourself as a member of the California Polytechnic State University or the Munich "
      + "University of Applied Sciences by clicking the link below: %n%s/activation/%s?key=%s";

  private static final String COMMENT_ACTIVATION_SUBJECT = "Your comment on Activitymeter";
  private static final String COMMENT_ACTIVATION_TEXT = "Hello %s!%nThank you for your submission! "
          + "Your comment will appear on Activitymeter as soon as you authenticate yourself as a "
          + "member of the California Polytechnic State University or the Munich University of "
          + "Applied Sciences by clicking the link below: %n%s/activation/comment/%s?key=%s";

  private static final String ACTIVITY_DELETE_SUBJECT = "Deleting your activity on Activitymeter";
  private static final String ACTIVITY_DELETE_TEXT = "Hello %s!%n You are about to delete your "
      + "activity. Please click the link below to confirm the deletion of your activity:%n"
      + "%s/activation/%d/delete?key=%s%n%n"
      + "If you did not initiate this action, you can ignore this email.";

  private static final String COMMENT_DELETE_SUBJECT = "Deleting your comment on Activitymeter";
  private static final String COMMENT_DELETE_TEXT = "Hello %s!%n You are about to delete your "
      + "comment. Please click the link below to confirm the deletion of your comment:%n"
      + "%s/activation/comment/%d/delete?key=%s%n%n"
      + "If you did not initiate this action, you can ignore this email.";

  private static final String NOTIFICATION_SUBJECT = "Someone commented on your Activity";
  private static final String NOTIFICATION_TEXT = "Hello %s!%nSomeone commented on your Activity. "
          + "Click on the link below to see your activity: %n%s/view/%s";

  private static final List<String> VALID_DOMAINS = Arrays.asList("calpoly.edu", "hm.edu");

  @Value("${email.name}")
  private String gmailUser;
  @Value("${email.password}")
  private String gmailPassword;
  @Value("${host.url}")
  private String host;

  private Session session = null;

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  /**
   * Sends activation mail for a post.
   * @param post Post to be activated
   * @param activationKey Activation key
   * @return if email was send successfully
   */
  public boolean sendActivationMail(Post post, String activationKey) {
    log.info("Try sending activation email to " + post.getEmail());
    return sendMail(post.getEmail(), ACTIVITY_ACTIVATION_SUBJECT,
              String.format(ACTIVITY_ACTIVATION_TEXT, post.getAuthor(), host,
                      post.getId(), activationKey));
  }

  /**
   * Sends activation mail for a comment.
   * @param comment Comment to be activated
   * @param activationKey Activation key
   * @return if email was send successfully
   */
  public boolean sendActivationMail(Comment comment, String activationKey) {
    log.info("Try sending comment activation email to " + comment.getEmail());
    return sendMail(comment.getEmail(), COMMENT_ACTIVATION_SUBJECT,
              String.format(COMMENT_ACTIVATION_TEXT, comment.getAuthor(), host,
                      comment.getId(), activationKey));
  }

  /**
   * Sends a deletion mail for a activity.
   * @param post Activity to delete
   * @param activationKey Activation key
   * @return if email was send successfully
   */
  public boolean sendDeleteMail(Post post, String activationKey) {
    log.info("Try sending deletion email to " + post.getEmail());
    return sendMail(post.getEmail(), ACTIVITY_DELETE_SUBJECT,
        String.format(ACTIVITY_DELETE_TEXT, post.getAuthor(), host,
            post.getId(), activationKey));
  }

  /**
   * Sends a deletion mail for a activity.
   * @param comment Comment to delete
   * @param activationKey Activation key
   * @return if email was send successfully
   */
  public boolean sendDeleteMail(Comment comment, String activationKey) {
    log.info("Try sending deletion email to " + comment.getEmail());
    return sendMail(comment.getEmail(), COMMENT_DELETE_SUBJECT,
        String.format(COMMENT_DELETE_TEXT, comment.getAuthor(), host,
            comment.getId(), activationKey));
  }

  /**
   * Sends notification mail about comment.
   * @param post Post which was commented on
   * @return if email was send successfully
   */
  public boolean sendNotificationMail(Post post) {
    log.info("Try sending notification email to " + post.getEmail());
    return sendMail(post.getEmail(), NOTIFICATION_SUBJECT,
              String.format(NOTIFICATION_TEXT, post.getAuthor(), host, post.getId()));
  }

  private boolean isValidAddress(String mailAddress) {
    return VALID_DOMAINS.contains(extractDomain(mailAddress));
  }

  private String extractDomain(String mailAddress) {
    String[] tmp = mailAddress.split("@", -1);
    if (tmp.length != 2) {
      throw new IllegalArgumentException();
    }
    return tmp[tmp.length - 1];
  }

  private boolean sendMail(String recipient, String subject, String text) {
    if (!isValidAddress(recipient)) {
      log.error("Invalid email: %s", recipient);
      return false;
    }
    try {
      Message msg = new MimeMessage(createSession());
      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
      msg.setSubject(subject);
      msg.setText(text);
      Transport.send(msg);
    } catch (MessagingException e) {
      log.error(e.toString());
      return false;
    }
    log.info("Mail send successfully!");
    return true;
  }

  private Session createSession() {
    if (session == null) {
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");
      session = Session.getInstance(props, new GMailAuthenticator(gmailUser, gmailPassword));
    }
    return session;
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
