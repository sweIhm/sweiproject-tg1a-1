package edu.hm.cs.se.activitymeter.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {

  @Column(name = "text", nullable = false)
  private String text;

  @Column(name = "author", nullable = false)
  private String author;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "published", nullable = false)
  private boolean published;

  AbstractEntity(String author, String text, String email, boolean published) {
    this.author = author;
    this.text = text;
    this.email = email;
    this.published = published;
  }

  AbstractEntity() {}

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }
}
