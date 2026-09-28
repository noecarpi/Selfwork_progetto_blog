package it.aulab.progetto_blog.services;

import java.util.List;

import it.aulab.progetto_blog.models.Comment;
import it.aulab.progetto_blog.models.Post;

public interface CommentService {

    List<Comment> readAll();
    Comment read(Long id);
    List<Comment> read(String email);
    List<Comment> read(Post post);
    Comment create(Comment comment);
    Comment update(Long id, Comment comment);
    void delete(Long id);
}
