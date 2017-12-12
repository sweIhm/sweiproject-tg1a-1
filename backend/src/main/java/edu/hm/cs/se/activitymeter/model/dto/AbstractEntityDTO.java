package edu.hm.cs.se.activitymeter.model.dto;

public class AbstractEntityDTO {

  private Long id;

  private String text;

  private String author;

  AbstractEntityDTO() {}

  AbstractEntityDTO(Long id, String author, String text) {
    this.id = id;
    this.text = text;
    this.author = author;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
