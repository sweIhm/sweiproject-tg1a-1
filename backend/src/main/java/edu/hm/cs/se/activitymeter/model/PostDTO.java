package edu.hm.cs.se.activitymeter.model;


public class PostDTO {

  private Long id;

  private String text;

  private String title;

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
