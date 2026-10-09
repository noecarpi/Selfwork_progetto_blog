package it.aulab.progetto_blog.dtos;

import java.time.LocalDate;

public class CommentDto {
    private Long id;
    private String body;
    private LocalDate date;
    private PostDto post;
    private AuthorDto author;
    
    public CommentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }
    
}
