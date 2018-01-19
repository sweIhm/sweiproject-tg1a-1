package edu.hm.cs.se.activitymeter.model.dto;

public class KeywordDTO  {
  private String content;
  private long count;

  public KeywordDTO(String content, long count) {
    this.content = content;
    this.count = count;
  }

  public String getContent() {
    return content;
  }

  public long getCount() {
    return count;
  }
}
