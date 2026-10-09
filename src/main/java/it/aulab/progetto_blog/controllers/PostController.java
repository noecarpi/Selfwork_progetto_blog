package it.aulab.progetto_blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.aulab.progetto_blog.dtos.AuthorDto;
import it.aulab.progetto_blog.dtos.PostDto;
import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.models.Post;
import it.aulab.progetto_blog.services.AuthorService;
import it.aulab.progetto_blog.services.PostService;

@Controller 
@RequestMapping ("/posts")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired 
    AuthorService authorService;

    @GetMapping
    public String postsView(Model viewModel){
        viewModel.addAttribute("title", "Posts");
        viewModel.addAttribute("posts", postService.readAll());
        return "posts";
    }

    @GetMapping("create")
    public String createPostView(Model viewModel){
        viewModel.addAttribute("title", "Create Posts");
        Post post = new Post();
        post.setAuthor(new Author());
        viewModel.addAttribute("post", post);
        viewModel.addAttribute("authors", authorService.readAll());
        return "createPost";
    }

    @GetMapping("{id}/edit")
    public String editPostView(@PathVariable Long id,Model viewModel){

        viewModel.addAttribute("title", "Edit Post");
        viewModel.addAttribute("post", postService.read(id));
        viewModel.addAttribute("authors", authorService.readAll());
        return "editPost";
    }

    @PostMapping
    public String createPost(@ModelAttribute("post") PostDto postDto){
        postService.create(postDto);
        return "redirect:/posts";
    }

    @PostMapping("{id}/edit")
    public String editPost(@PathVariable Long id, @ModelAttribute("post") PostDto postDto){
        postService.update(id, postDto);
        return "redirect:/posts";
    }

    @PostMapping("{id}/delete")
    public String deletePost(@PathVariable Long id){
        postService.delete(id);
        return "redirect:/posts";
    }
}
