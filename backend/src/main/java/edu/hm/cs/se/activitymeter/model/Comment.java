package edu.hm.cs.se.activitymeter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Comment")
public class Comment extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_seq")
  @SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq", allocationSize = 1)
  @Column(name = "commentId")
  private Long id;

  public Comment() {
    // Leerer Konstruktor für JPA
  }

  public Comment(String text, String author, String email, boolean published) {
    super(author, text, email, published);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
