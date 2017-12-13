package edu.hm.cs.se.activitymeter.controller.email;

import edu.hm.cs.se.activitymeter.model.Comment;
import edu.hm.cs.se.activitymeter.model.Post;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEmailController {
  private static final List<String> VALID_DOMAINS = Arrays.asList("calpoly.edu", "hm.edu");

  protected Logger log = LoggerFactory.getLogger(getClass());

  /**
   * Sends activation mail for a post.
   * @param post Post to be activated
   * @param activationKey Activation key
   */
  public void sendActivationMail(Post post, String activationKey) {
    log.info("Try sending activation email to {}", post.getEmail());
    sendMail(post.getEmail(), EmailTexts.ACTIVITY_ACTIVATION_SUBJECT,
        String.format(EmailTexts.ACTIVITY_ACTIVATION_TEXT, post.getAuthor(), getHost(),
            post.getId(), activationKey));
  }

  /**
   * Sends activation mail for a comment.
   * @param comment Comment to be activated
   * @param activationKey Activation key
   */
  public void sendActivationMail(Comment comment, String activationKey) {
    log.info("Try sending comment activation email to {}", comment.getEmail());
    sendMail(comment.getEmail(), EmailTexts.COMMENT_ACTIVATION_SUBJECT,
        String.format(EmailTexts.COMMENT_ACTIVATION_TEXT, comment.getAuthor(), getHost(),
            comment.getId(), activationKey));
  }

  /**
   * Sends a deletion mail for a activity.
   * @param post Activity to delete
   * @param activationKey Activation key
   */
  public void sendDeleteMail(Post post, String activationKey) {
    log.info("Try sending deletion email to {}", post.getEmail());
    sendMail(post.getEmail(), EmailTexts.ACTIVITY_DELETE_SUBJECT,
        String.format(EmailTexts.ACTIVITY_DELETE_TEXT, post.getAuthor(), getHost(),
            post.getId(), activationKey));
  }

  /**
   * Sends a deletion mail for a activity.
   * @param comment Comment to delete
   * @param activationKey Activation key
   */
  public void sendDeleteMail(Comment comment, String activationKey) {
    log.info("Try sending deletion email to {}", comment.getEmail());
    sendMail(comment.getEmail(), EmailTexts.COMMENT_DELETE_SUBJECT,
        String.format(EmailTexts.COMMENT_DELETE_TEXT, comment.getAuthor(), getHost(),
            comment.getId(), activationKey));
  }

  /**
   * Notifies all participants of an conversation about a new comment.
   * @param post Post where conversation takes place
   * @param trigger Comment triggering notification
   */
  public void sendNotificationMails(Post post, Comment trigger) {
    Map<String, List<Comment>> map = groupCommentsByEmail(post);
    if (map.containsKey(post.getEmail())) {
      map.remove(post.getEmail());
    }
    if (map.containsKey(trigger.getEmail())) {
      map.remove(trigger.getEmail());
    }
    notifyOP(post, trigger);
    map.entrySet().stream()
        .map(Map.Entry::getValue)
        .forEach(cs -> notifiyCommentator(post, cs));
  }

  private void notifyOP(Post post, Comment trigger) {
    if (post.getEmail().equals(trigger.getEmail())) {
      log.info("OP commented own post - not sending email to OP");
      return;
    }
    log.info("Try sending notification email to {} (OP)", post.getEmail());
    sendMail(post.getEmail(), EmailTexts.NOTIFICATION_SUBJECT,
        String.format(EmailTexts.NOTIFICATION_TEXT, post.getAuthor(), getHost(), post.getId()));
  }

  private void notifiyCommentator(Post post, List<Comment> comments) {
    String author = getAuthorByComments(comments);
    String email = comments.get(0).getEmail();
    log.info("Try sending notification email to {} (Commentator)", email);
    sendMail(email, EmailTexts.NOTIFICATION_COMMENT_SUBJECT,
        String.format(EmailTexts.NOTIFICATION_COMMENT_TEXT, author, getHost(), post.getId()));
  }

  protected boolean isValidAddress(String mailAddress) {
    return VALID_DOMAINS.contains(extractDomain(mailAddress));
  }

  private String extractDomain(String mailAddress) {
    String[] tmp = mailAddress.split("@", -1);
    if (tmp.length != 2) {
      throw new IllegalArgumentException();
    }
    return tmp[1];
  }

  private Map<String, List<Comment>> groupCommentsByEmail(Post post) {
    return post.getComments().stream().filter(Comment::isPublished)
        .collect(Collectors.groupingBy(Comment::getEmail, Collectors.toList()));
  }

  private String getAuthorByComments(List<Comment> comments) {
    List<String> pseudonyms = comments.stream()
        .map(Comment::getAuthor).distinct()
        .collect(Collectors.toList());
    String author = pseudonyms.get(0);
    if (pseudonyms.size() > 1) {
      StringJoiner joiner = new StringJoiner(", ", " (a.k.a. ", ")");
      pseudonyms.remove(author);
      pseudonyms.forEach(joiner::add);
      author += joiner.toString();
    }
    return author;
  }

  /**
   * Sends mail. To be implemented by concrete controller.
   * @param recipient Recipient's address
   * @param subject Subject of mail to be send
   * @param text Text of mail to be send
   * @throws IllegalArgumentException Thrown for illegal email format
   */
  protected abstract boolean sendMail(String recipient, String subject, String text);

  protected abstract String getHost();

  public abstract String generateKey();
}
