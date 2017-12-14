package edu.hm.cs.se.activitymeter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Comment")
public class Comment extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_seq")
  @SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq", allocationSize = 1)
  @Column(name = "comment_id")
  private Long id;

  public Comment() {
    // Leerer Konstruktor für JPA
  }

  @OneToOne(mappedBy = "comment", fetch = FetchType.LAZY)
  private ActivationKeyComment key;

  @ManyToOne(fetch = FetchType.LAZY)

  @JsonIgnore
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  public Comment(String text, String author, String email, boolean published, Post post) {
    super(author, text, email, published);
    this.post = post;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }
}
