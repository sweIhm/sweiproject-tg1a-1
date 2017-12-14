package edu.hm.cs.se.activitymeter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Keyword")
public class Keyword {

  @Column(name = "content", nullable = false, unique = true)
  private String content;

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "keyword_id_seq")
  @SequenceGenerator(name = "keyword_id_seq", sequenceName = "keyword_id_seq", allocationSize = 1)
  @Column(name = "keyword_id")
  private long id;

  @JsonIgnore
  @ManyToMany(mappedBy = "keywords")
  private List<Post> posts;

  public Keyword() {
    // Empty for JPA
  }

  public Keyword(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public List<Post> getPosts() {
    return posts;
  }
}
