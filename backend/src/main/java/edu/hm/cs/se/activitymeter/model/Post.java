package edu.hm.cs.se.activitymeter.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Post")
public class Post extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_seq")
  @SequenceGenerator(name = "post_id_seq", sequenceName = "post_id_seq", allocationSize = 1)
  @Column(name = "post_id")
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
  private ActivationKey key;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
  private List<Comment> comments;

  @ManyToMany(mappedBy = "posts")
  private List<Keyword> keywords = new ArrayList();

  public Post() {
    // Leerer Konstruktor für JPA
  }

  public Post(String author, String title, String text, String email, boolean published,
      List<Keyword> keywordList) {
    super(author, text, email, published);
    this.title = title;
    this.keywords.addAll(keywordList);
  }

  public List<Comment> getComments() {
    return comments;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Keyword> getKeywords() {
    return keywords;
  }

  public void setKeywords(List<Keyword> keywords) {
    this.keywords = keywords;
  }
}