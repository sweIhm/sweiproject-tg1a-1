package edu.hm.cs.se.activitymeter.model.dto;

import edu.hm.cs.se.activitymeter.model.Post;

public class PostDTO extends AbstractEntityDTO {

  private String title;

  public PostDTO() {}

  public PostDTO(Post p) {
    super(p.getId(), p.getAuthor(), p.getText());
    this.title = p.getTitle();
  }

  public String getTitle() {
    return title;
  }

  void setTitle(String title) {
    this.title = title;
  }
}
