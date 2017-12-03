package edu.hm.cs.se.activitymeter.model;

import javax.persistence.*;


@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="comment_id_seq")
    @SequenceGenerator(name="comment_id_seq", sequenceName="comment_id_seq", allocationSize=1)
    @Column(name = "commentId")
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "published", nullable = false)
    private boolean published;

    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
    private ActivationKeyComment key;

    public Comment() {
        // Leerer Konstruktor für JPA
    }

    public Comment(String text, String author, String email, boolean published) {
        this.text = text;
        this.author = author;
        this.email = email;
        this.published = published;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

}