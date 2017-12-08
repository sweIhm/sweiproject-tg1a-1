package edu.hm.cs.se.activitymeter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment_key")
public class CommentActivationKey {

  @Column(name = "comment_key", unique = true, nullable = false)
  private String key;

  @Id
  @Column(name = "comment_id")
  private long commentId;

  @OneToOne
  @JoinColumn(name = "comment_id")
  private Comment comment;

  public CommentActivationKey() {
    // Leerer Konstruktor für JPA
  }

  public CommentActivationKey(long commentId, String key) {
    this.key = key;
    this.commentId = commentId;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public long getCommentId() {
    return commentId;
  }

  public void setCommentId(long commentId) {
    this.commentId = commentId;
  }

  public Comment getComment() {
    return comment;
  }
}