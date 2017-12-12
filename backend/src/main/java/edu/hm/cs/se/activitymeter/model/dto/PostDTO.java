package edu.hm.cs.se.activitymeter.model.dto;

import edu.hm.cs.se.activitymeter.model.Keyword;
import edu.hm.cs.se.activitymeter.model.Post;
import java.util.List;

public class PostDTO extends AbstractEntityDTO {

  private String title;

  private List<Keyword> tags;

  public PostDTO() {}

  public PostDTO(Post p) {
    super(p.getId(), p.getAuthor(), p.getText());
    this.title = p.getTitle();
    this.tags = p.getKeywords();
  }

  public String getTitle() {
    return title;
  }

  void setTitle(String title) {
    this.title = title;
  }

  public List<Keyword> getTags() {
    return tags;
  }
}
