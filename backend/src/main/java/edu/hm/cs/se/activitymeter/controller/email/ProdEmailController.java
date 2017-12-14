package edu.hm.cs.se.activitymeter.controller.email;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"test", "prod"})
@Primary
public class ProdEmailController extends AbstractEmailController {

  @Value("${email.name}")
  private String gmailUser;
  @Value("${email.password}")
  private String gmailPassword;
  @Value("${host.url}")
  private String host;

  private Session session = null;

  @Override
  protected boolean sendMail(String recipient, String subject, String text) {
    if (!isValidAddress(recipient)) {
      log.error("Invalid email: {}", recipient);
      throw new IllegalArgumentException();
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

  @Override
  protected String getHost() {
    return host;
  }

  /**
   * Generates activation key.
   * @return activation key
   */
  @Override
  public String generateKey() {
    String result = UUID.randomUUID().toString();
    result = result.replace("-","");
    return result;
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
}
