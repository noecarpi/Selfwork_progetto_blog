package it.aulab.progetto_blog.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.progetto_blog.dtos.CommentDto;
import it.aulab.progetto_blog.dtos.PostDto;
import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.models.Comment;
import it.aulab.progetto_blog.models.Post;
import it.aulab.progetto_blog.repositories.AuthorRepository;
import it.aulab.progetto_blog.repositories.CommentRepository;
import it.aulab.progetto_blog.repositories.PostRepository;

@Service 
public class CommentServiceImpl implements CommentService {

    @Autowired 
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CommentDto> readAll() {
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (Comment comment : commentRepository.findAll()) { 
            CommentDto dto = mapper.map(comment, CommentDto.class);

            dto.getAuthor().setFullname(comment.getAuthor().getName()+" "+comment.getAuthor().getSurname());

            dto.getPost().getTitle();

            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public CommentDto read(Long id) {
        Optional<Comment> optComment = commentRepository.findById(id);
        if (optComment.isPresent()) {
            return mapper.map(optComment.get(), CommentDto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment id = "+ id + " not found");
        }
    }

    @Override
    public List<CommentDto> read(Author author) {
        if (author==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (Comment comment : commentRepository.findByAuthor(author)) {
            dtos.add(mapper.map(comment, CommentDto.class));
        }
        return dtos;
    }

    @Override
    public List<CommentDto> read(Post post) {
        if (post==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (Comment comment : commentRepository.findByPost(post)) {
            dtos.add(mapper.map(comment, CommentDto.class));
        }
        return dtos;
    }

    @Override
    public CommentDto create(CommentDto commentDto) {
        if (commentDto.getAuthor()==null||commentDto.getBody()==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Comment comment = mapper.map(commentDto, Comment.class);
        comment.setDate(LocalDate.now());
        return mapper.map(commentRepository.save(comment), CommentDto.class);
    }

    @Override
    public CommentDto update(Long id, CommentDto commentDto) {
        if (commentRepository.existsById(id)) {
            Comment comment = commentRepository.findById(id).get();
            comment.setBody(commentDto.getBody());
            comment.setDate(commentDto.getDate());
            comment.setDate(LocalDate.now());
            return mapper.map(commentRepository.save(comment), CommentDto.class) ;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void delete(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        }
    }

}
