package it.aulab.progetto_blog.services;

import java.util.List;

import it.aulab.progetto_blog.dtos.PostDto;
import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.models.Post;

public interface PostService {
    
    List<PostDto> readAll();
    PostDto read(Long id);
    List<PostDto> read(String title);
    List<PostDto> read(Author author);
    PostDto create(PostDto postDto);
    PostDto update(Long id, PostDto postDto);
    void delete(Long id);
}
