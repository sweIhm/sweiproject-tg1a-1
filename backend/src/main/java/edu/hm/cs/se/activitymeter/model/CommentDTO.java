package edu.hm.cs.se.activitymeter.model;

import javax.persistence.*;


@Entity
@Table(name = "Activity")
public class CommentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="activity_id_seq")
    @SequenceGenerator(name="activity_id_seq", sequenceName="activity_id_seq", allocationSize=1)
    @Column(name = "id")
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "author", nullable = false)
    private String author;

    public CommentDTO() {
        // Leerer Konstruktor für JPA
    }

    public CommentDTO(String text, String title, String author, String email, boolean published) {
        this.text = text;
        this.author = author;
    }

    public CommentDTO(Comment k) {
        this.text = k.getText();
        this.author = k.getAuthor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}