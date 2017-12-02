package edu.hm.cs.se.activitymeter.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Keyword")
public class Keyword {

    @Column(name = "content", nullable = false, unique = true)
    private String content;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="keyword_id_seq")
    @SequenceGenerator(name="keyword_id_seq", sequenceName="keyword_id_seq", allocationSize=1)
    @Column(name = "id")
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
