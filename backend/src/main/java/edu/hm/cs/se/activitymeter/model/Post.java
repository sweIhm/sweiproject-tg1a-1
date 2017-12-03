package edu.hm.cs.se.activitymeter.model;

import javax.persistence.*;


@Entity
@Table(name = "Activity")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="activity_id_seq")
    @SequenceGenerator(name="activity_id_seq", sequenceName="activity_id_seq", allocationSize=1)
    @Column(name = "id")
    private Long id;

    @Column(name = "text", nullable = false, length = 1000)
    private String text;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "published", nullable = false)
    private boolean published;

    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
    private ActivationKey key;

    public Post() {
        // Leerer Konstruktor für JPA
    }

    public Post(String text, String title, String author, String email, boolean published) {
        this.text = text;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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