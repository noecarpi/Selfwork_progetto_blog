package it.aulab.progetto_blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.aulab.progetto_blog.dtos.CommentDto;
import it.aulab.progetto_blog.models.Comment;
import it.aulab.progetto_blog.models.Post;
import it.aulab.progetto_blog.repositories.CommentRepository;
import it.aulab.progetto_blog.services.CommentService;

@RestController 
@RequestMapping("/api/comments")
public class CommentRestController {
    
    @Autowired
    CommentService commentService;

    @GetMapping 
    public List<CommentDto> getAllComment(){
        return commentService.readAll();
    }
    
    @GetMapping("{id}")
    public CommentDto getComment(@PathVariable("id") Long id){
        return commentService.read(id);
    }
    
    
    @PostMapping 
    public CommentDto createComment(@RequestBody CommentDto commentDto){
        return commentService.create(commentDto);
    } 

    @PutMapping("{id}")
    public CommentDto updateComment(@PathVariable ("id") Long id, @RequestBody CommentDto commentDto){
        return commentService.update(id,commentDto);
    } 

    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable("id") Long id){
        commentService.delete(id);
    }
}
