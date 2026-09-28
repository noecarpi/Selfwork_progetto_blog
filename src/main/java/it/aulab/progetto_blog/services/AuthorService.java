package it.aulab.progetto_blog.services;

import java.util.List;

import it.aulab.progetto_blog.models.Author;

public interface AuthorService {

    List<Author> readAll();
    Author read(Long id);
    List<Author> read(String email);
    List<Author> read(String firstname,String lastname);
    Author create(Author author);
    Author update(Long id, Author author);
    void delete(Long id);
}
