package com.mycompany.RestDbAPI.model.info;


public class CommentInfo {
    
    private Long id;
    private Long article;
    private String author;
    private String text;

    public CommentInfo() {
    }

    public CommentInfo(Long id, Long article, String author, String text) {
        this.id = id;
        this.article = article;
        this.author = author;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticle() {
        return article;
    }

    public void setArticle(Long article) {
        this.article = article;
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
