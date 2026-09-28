package it.aulab.progetto_blog.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.models.Comment;
import it.aulab.progetto_blog.models.Post;


public interface CommentRepository extends ListCrudRepository<Comment, Long> {

    List<Comment> findByEmail(String email);
    List<Comment> findByPost(Post post);
}
