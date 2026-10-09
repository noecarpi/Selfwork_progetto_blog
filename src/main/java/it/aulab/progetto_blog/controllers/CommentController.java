package it.aulab.progetto_blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.aulab.progetto_blog.dtos.CommentDto;
import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.models.Comment;
import it.aulab.progetto_blog.models.Post;
import it.aulab.progetto_blog.services.AuthorService;
import it.aulab.progetto_blog.services.CommentService;
import it.aulab.progetto_blog.services.PostService;

@Controller 
@RequestMapping ("/comments")
public class CommentController {

    @Autowired 
    CommentService commentService;

    @Autowired
    PostService postService;

    @Autowired 
    AuthorService authorService;

    @GetMapping
    public String commentsView(Model viewModel){
        viewModel.addAttribute("title", "Comments");
        viewModel.addAttribute("comments", commentService.readAll());
        return "comments";
    }

    @GetMapping ("create")
    public String createCommentView(@ModelAttribute("comment") CommentDto commentDto,Model viewModel){
        viewModel.addAttribute("title", "Create Comment");
        Comment comment = new Comment();
        comment.setPost(new Post());
        comment.setAuthor(new Author());
        viewModel.addAttribute("comment", comment);
        viewModel.addAttribute("posts", postService.readAll());
        viewModel.addAttribute("authors", authorService.readAll());
        return "createComment";
    }

    @GetMapping("{id}/edit")
    public String editCommentView(@PathVariable Long id,Model viewModel){
        viewModel.addAttribute("title", "Edit Comment");
        viewModel.addAttribute("comment", commentService.read(id));
        viewModel.addAttribute("posts", postService.readAll());
        viewModel.addAttribute("authors", authorService.readAll());
        return "editComment";
    }

    @PostMapping
    public String createComment(@ModelAttribute("comment") CommentDto commentDto){
        commentService.create(commentDto);
        return "redirect:/comments";
    }

    @PostMapping("{id}/edit")
    public String editComment(@PathVariable Long id, @ModelAttribute("comment") CommentDto commentDto){
        commentService.update(id, commentDto);
        return "redirect:/comments";
    }

    @PostMapping("{id}/delete")
    public String deleteComment(@PathVariable Long id){
        commentService.delete(id);
        return "redirect:/comments";
    }
}
