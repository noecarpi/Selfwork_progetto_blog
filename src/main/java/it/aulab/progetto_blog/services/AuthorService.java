package it.aulab.progetto_blog.services;

import java.util.List;

import it.aulab.progetto_blog.dtos.AuthorDto;
import it.aulab.progetto_blog.models.Author;

public interface AuthorService {

    List<AuthorDto> readAll();
    AuthorDto read(Long id);
    List<AuthorDto> read(String email);
    List<AuthorDto> read(String firstname,String lastname);
    AuthorDto create(AuthorDto authorDto);
    AuthorDto update(Long id, AuthorDto authorDto);
    void delete(Long id);
}
