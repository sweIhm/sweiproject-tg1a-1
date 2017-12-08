package edu.hm.cs.se.activitymeter.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Activity")
public class Post extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_id_seq")
  @SequenceGenerator(name = "activity_id_seq", sequenceName = "activity_id_seq", allocationSize = 1)
  @Column(name = "id")
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
  private ActivationKey key;

  @ManyToMany()
  private List<Keyword> keywords;

  public Post() {
    // Leerer Konstruktor für JPA
  }

  public Post(String author, String title, String text, String email, boolean published, List<Keyword> keywords) {
    super(author, text, email, published);
    this.title = title;
    this.keywords = keywords;
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