package edu.hm.cs.se.activitymeter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Activity")
public class PostDTO {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_id_seq")
  @SequenceGenerator(name = "activity_id_seq", sequenceName = "activity_id_seq", allocationSize = 1)
  @Column(name = "id")
  private Long id;

  @Column(name = "text", nullable = false)
  private String text;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "author", nullable = false)
  private String author;

  public PostDTO(){}

  PostDTO(String text, String title, String author) {
    this.text = text;
    this.title = title;
    this.author = author;
  }

  public PostDTO(Post p) {
    this(p.getText(), p.getTitle(), p.getAuthor());
    this.id = p.getId();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getTitle() {
    return title;
  }

  void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  void setAuthor(String author) {
    this.author = author;
  }
}
