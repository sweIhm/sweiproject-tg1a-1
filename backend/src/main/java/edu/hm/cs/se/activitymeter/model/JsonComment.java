package edu.hm.cs.se.activitymeter.model;

import javax.persistence.*;


public class JsonComment extends AbstractEntity {

  private Long id;


  public JsonComment() {
    // Leerer Konstruktor für JPA
  }


  public JsonComment(String text, String author, String email, boolean published, long postId) {
    super(author, text, email, published);

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}