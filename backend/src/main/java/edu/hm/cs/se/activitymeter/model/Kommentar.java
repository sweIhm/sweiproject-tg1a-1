package edu.hm.cs.se.activitymeter.model;

import javax.persistence.*;


@Entity
@Table(name = "Comment")
public class Kommentar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="activity_id_seq")
    @SequenceGenerator(name="activity_id_seq", sequenceName="activity_id_seq", allocationSize=1)
    @Column(name = "Comment_id")
    private Long comment_id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "published", nullable = false)
    private boolean published;

    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY)
    private ActivationKey key;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Post post;

    public Kommentar() {
        // Leerer Konstruktor für JPA
    }

    public Kommentar(String text, String title, String author, String email, boolean published) {
        this.text = text;
        this.author = author;
        this.email = email;
        this.published = published;
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setCommentid(Long id) {
        this.comment_id = id;
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


    public Post getPost() {return post;}

}