package it.aulab.progetto_blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.aulab.progetto_blog.dtos.AuthorDto;
import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.services.AuthorService;



@Controller
@RequestMapping("/authors") 
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public String authorsView(Model viewModel) {
        viewModel.addAttribute("title", "Authors");
        viewModel.addAttribute("authors", authorService.readAll());
        return "authors";
    }

    // per una possibile pagina per singolo autore che non sto ancora usando
    // @GetMapping("{id}") 
    // public String showAuthor(@PathVariable Long id, Model viewModel){
    //     viewModel.addAttribute("title", "Show Author");
    //     viewModel.addAttribute("author", authorService.read(id));
    //     return "showAuthor";
    // }

    @GetMapping("create") 
    public String createAuthorView(Model viewModel) {
        viewModel.addAttribute("title", "Create Author");
        viewModel.addAttribute("author", new Author());
        return "createAuthor";
    }

    @GetMapping("{id}/edit") 
    public String editAuthorShow(@PathVariable Long id, Model viewModel){
        viewModel.addAttribute("title", "Edit Author");
        viewModel.addAttribute("author", authorService.read(id));
        return "editAuthor";
    }

    @PostMapping
    public String createAuthor(@ModelAttribute("author") AuthorDto authorDto){
        authorService.create(authorDto);
        return "redirect:/authors";
    }
    
    @PostMapping("{id}/edit")
    public String editAuthor(@PathVariable Long id, @ModelAttribute("author") AuthorDto authorDto){
        authorService.update(id , authorDto);
        return "redirect:/authors";
    }

    @PostMapping("{id}/delete")
    public String deleteAuthor(@PathVariable Long id){
        authorService.delete(id);
        return "redirect:/authors";
    }
}
