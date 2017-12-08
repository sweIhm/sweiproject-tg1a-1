package edu.hm.cs.se.activitymeter.model.dto;

import edu.hm.cs.se.activitymeter.model.Comment;

public class CommentDTO extends AbstractEntityDTO {

  public CommentDTO() {}

  public CommentDTO(Comment k) {
    super(k.getId(), k.getAuthor(), k.getText());
  }
}