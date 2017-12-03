package edu.hm.cs.se.activitymeter.model;

public class CommentDTO {

    private Long id;

    private String text;

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