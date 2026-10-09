package it.aulab.progetto_blog.services;

import java.util.List;

import it.aulab.progetto_blog.dtos.CommentDto;
import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.models.Comment;
import it.aulab.progetto_blog.models.Post;

public interface CommentService {

    List<CommentDto> readAll();
    CommentDto read(Long id);
    List<CommentDto> read(Author author);
    List<CommentDto> read(Post post);
    CommentDto create(CommentDto commentDto);
    CommentDto update(Long id, CommentDto commentDto);
    void delete(Long id);
}
