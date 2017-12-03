package edu.hm.cs.se.activitymeter.model;

import javax.persistence.*;

@Entity
@Table(name = "Keys")
public class ActivationKey {

  @Column(name = "key", unique = true, nullable = false)
  private String key;

  @Id
  @Column(name = "id")
  private long postId;

  @OneToOne
  @JoinColumn(name = "id")
  private Post post;

  public ActivationKey() {
    // Leerer Konstruktor für JPA
  }

  public ActivationKey(long postId, String key) {
    this.key = key;
    this.postId = postId;
  }

  public String getKey() {
    return key;
  }

  void setKey(String key) {
    this.key = key;
  }

  long getPostId() {
    return postId;
  }

  void setPostId(long postId) {
    this.postId = postId;
  }

  public Post getPost() {
    return post;
  }
}