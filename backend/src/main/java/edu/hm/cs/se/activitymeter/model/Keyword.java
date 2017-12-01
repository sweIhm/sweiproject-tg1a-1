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

    @ManyToMany(mappedBy = "id")
    private List<Post> posts;

    public Keyword(String content) {
        this.content = content;
        this.posts = new ArrayList<>();
    }

    public String getContent() {
        return content;
    }

    public long getKeywordId() {
        return keywordId;
    }

    public void addPost(Post p) {
        posts.add(p);
    }

    public List<Post> getPosts() {
        return posts;
    }
}
