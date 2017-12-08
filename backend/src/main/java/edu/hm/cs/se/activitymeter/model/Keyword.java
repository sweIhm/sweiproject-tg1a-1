package edu.hm.cs.se.activitymeter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Keyword")
public class Keyword {

  @Column(name = "content", nullable = false, unique = true)
  private String content;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "keyword_id_seq")
  @SequenceGenerator(name = "keyword_id_seq", sequenceName = "keyword_id_seq", allocationSize = 1)
  @Column(name = "tag_id")
  private long keywordId;

  public Keyword(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public long getKeywordId() {
    return keywordId;
  }

}
