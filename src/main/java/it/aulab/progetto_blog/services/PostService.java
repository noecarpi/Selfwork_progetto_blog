package it.aulab.progetto_blog.services;

import java.util.List;

import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.models.Post;

public interface PostService {
    
    List<Post> readAll();
    Post read(Long id);
    List<Post> read(String title);
    List<Post> read(Author author);
    Post create(Post post);
    Post update(Long id, Post post);
    void delete(Long id);
}
