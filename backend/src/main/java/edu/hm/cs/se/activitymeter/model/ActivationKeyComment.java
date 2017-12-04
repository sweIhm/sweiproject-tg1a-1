package edu.hm.cs.se.activitymeter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Keys")
public class ActivationKeyComment {

  @Column(name = "key", unique = true, nullable = false)
  private String key;

  @Id
  @Column(name = "id")
  private long commentId;

  @OneToOne
  @JoinColumn(name = "id")
  private Comment comment;

  public ActivationKeyComment() {
    // Leerer Konstruktor für JPA
  }

  public ActivationKeyComment(long commentId, String key) {
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