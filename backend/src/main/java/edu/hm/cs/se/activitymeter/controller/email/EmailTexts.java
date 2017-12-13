package edu.hm.cs.se.activitymeter.controller.email;

public class EmailTexts {

  public static final String ACTIVITY_ACTIVATION_SUBJECT = "Your activity on Activitymeter";
  public static final String ACTIVITY_ACTIVATION_TEXT = "Hello %s!%nThank you for your "
          + "submission! Your activity will appear on Activitymeter as soon as you authenticate "
          + "yourself as a member of the California Polytechnic State University or the Munich "
          + "University of Applied Sciences by clicking the link below: %n%s/activation/%s?key=%s";

  public static final String COMMENT_ACTIVATION_SUBJECT = "Your comment on Activitymeter";
  public static final String COMMENT_ACTIVATION_TEXT = "Hello %s!%nThank you for your submission! "
          + "Your comment will appear on Activitymeter as soon as you authenticate yourself as a "
          + "member of the California Polytechnic State University or the Munich University of "
          + "Applied Sciences by clicking the link below: %n%s/activation/comment/%s?key=%s";

  public static final String ACTIVITY_DELETE_SUBJECT = "Deleting your activity on Activitymeter";
  public static final String ACTIVITY_DELETE_TEXT = "Hello %s!%n You are about to delete your "
          + "activity. Please click the link below to confirm the deletion of your activity:%n"
          + "%s/activation/%d/delete?key=%s%n%n"
          + "If you did not initiate this action, you can ignore this email.";

  public static final String COMMENT_DELETE_SUBJECT = "Deleting your comment on Activitymeter";
  public static final String COMMENT_DELETE_TEXT = "Hello %s!%n You are about to delete your "
          + "comment. Please click the link below to confirm the deletion of your comment:%n"
          + "%s/activation/comment/%d/delete?key=%s%n%n"
          + "If you did not initiate this action, you can ignore this email.";

  public static final String NOTIFICATION_SUBJECT = "Someone commented on your Activity";
  public static final String NOTIFICATION_TEXT = "Hello %s!%nSomeone commented on your Activity. "
          + "Click on the link below to see your activity: %n%s/view/%s";

  public static final String NOTIFICATION_COMMENT_SUBJECT = "Someone commented on the same"
          + "Activity as you";
  public static final String NOTIFICATION_COMMENT_TEXT = "Hello %s!%nSomeone commented on the "
          + "same Activity as you. Click on the link below to see the activity: %n%s/view/%s";

  private EmailTexts() {
    // hide default-Constructor
  }
}
