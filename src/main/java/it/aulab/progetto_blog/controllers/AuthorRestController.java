package it.aulab.progetto_blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.progetto_blog.dtos.AuthorDto;
import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.models.Post;
import it.aulab.progetto_blog.repositories.AuthorRepository;
import it.aulab.progetto_blog.services.AuthorService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



// diversamente da controller, non devo specificare @responseBody
@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {

    
    @Autowired
    AuthorService authorService;


    // @RequestMapping(method=RequestMethod.GET)
    @GetMapping
    public List<AuthorDto> getAllAuthors(){
        return authorService.readAll();
    }

    // @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    @GetMapping("{id}")
    public AuthorDto getAuthor(@PathVariable("id") Long id){
        return authorService.read(id);
    }
    
    
    @PostMapping //(consumes = "application/json") come default quindi ometto dalla funz
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto){
        return authorService.create(authorDto);
    } 

    @PutMapping("{id}")
    public AuthorDto updateAuthor(@PathVariable ("id") Long id, @RequestBody AuthorDto authorDto){
        // author.setId(id);
        return authorService.update(id,authorDto);
    } 

    @DeleteMapping("{id}")
    public void deleteAuthor(@PathVariable("id") Long id){
        authorService.delete(id);
    }
}
