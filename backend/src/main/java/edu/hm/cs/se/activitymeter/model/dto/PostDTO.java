package edu.hm.cs.se.activitymeter.model.dto;

import edu.hm.cs.se.activitymeter.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostDTO extends AbstractEntityDTO {

  private String title;

  private List<String> keywords;

  public PostDTO() {}

  public PostDTO(Post p) {
    super(p.getId(), p.getAuthor(), p.getText());
    this.title = p.getTitle();
    this.keywords = p.getKeywords().stream().map(x -> x.getContent()).collect(Collectors.toList());
  }

  public String getTitle() {
    return title;
  }

  void setTitle(String title) {
    this.title = title;
  }

}
