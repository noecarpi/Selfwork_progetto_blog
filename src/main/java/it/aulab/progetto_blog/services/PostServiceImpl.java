package it.aulab.progetto_blog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.progetto_blog.dtos.PostDto;
import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.models.Comment;
import it.aulab.progetto_blog.models.Post;
import it.aulab.progetto_blog.repositories.PostRepository;

@Service 
public class PostServiceImpl implements PostService{

    @Autowired 
    private PostRepository postRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<PostDto> readAll() {
        List<PostDto> dtos = new ArrayList<PostDto>();
        for (Post post : postRepository.findAll()) {
            PostDto dto = mapper.map(post, PostDto.class);

            dto.getAuthor().setFullname(post.getAuthor().getName()+" "+post.getAuthor().getSurname());

            dto.setNumberOfComments(post.getComments().size());

            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public PostDto read(Long id) {
        Optional<Post> optPost = postRepository.findById(id);
        if (optPost.isPresent()) {
            return mapper.map(optPost.get(),PostDto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post id = "+ id + " not found");
        }
    }

    @Override
    public List<PostDto> read(String title) {
        if (title==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        List<PostDto> dtos = new ArrayList<PostDto>();
        for (Post post : postRepository.findByTitle(title)) {
            dtos.add(mapper.map(post, PostDto.class));
        }
        return dtos;
    }

    @Override
    public List<PostDto> read(Author author) {
        if (author==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        List<PostDto> dtos = new ArrayList<PostDto>();
        for (Post post : postRepository.findByAuthor(author)) {
            dtos.add(mapper.map(post, PostDto.class));
        }
        return dtos;
    }

    @Override
    public PostDto create(PostDto postDto) {
        if (postDto.getTitle()==null||postDto.getBody()==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Post post = mapper.map(postDto, Post.class);
        return mapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public PostDto update(Long id, PostDto postDto) {
        if (postRepository.existsById(id)) {
            Post post = postRepository.findById(id).get();
            post.setTitle(postDto.getTitle());
            post.setBody(postDto.getBody());
            post.setPublishDate(postDto.getPublishDate());
            return mapper.map(postRepository.save(post),PostDto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void delete(Long id) {
        if (postRepository.existsById(id)) {
            Post post = postRepository.findById(id).get();
            List<Comment> postComments = post.getComments();
            for (Comment comment : postComments) {
                commentService.delete(comment.getId());
            }
            postRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
    }


}
