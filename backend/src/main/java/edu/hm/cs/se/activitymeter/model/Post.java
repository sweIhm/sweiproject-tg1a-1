package edu.hm.cs.se.activitymeter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

  public Post() {
    // Leerer Konstruktor für JPA
  }

  public Post(String author, String title, String text, String email, boolean published) {
    super(author, text, email, published);
    this.title = title;
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
}