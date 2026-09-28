package it.aulab.progetto_blog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Override
    public List<Post> readAll() {
        return postRepository.findAll();
    }

    @Override
    public Post read(Long id) {
        Optional<Post> optPost = postRepository.findById(id);
        if (optPost.isPresent()) {
            return optPost.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post id = "+ id + " not found");
        }
    }

    @Override
    public List<Post> read(String title) {
        if (title==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return postRepository.findByTitle(title);
    }

    @Override
    public List<Post> read(Author author) {
        if (author==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return postRepository.findByAuthor(author);
    }

    @Override
    public Post create(Post post) {
        if (post.getTitle()==null||post.getBody()==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return postRepository.save(post);
    }

    @Override
    public Post update(Long id, Post post) {
        if (postRepository.existsById(id)) {
            post.setId(id);
            return postRepository.save(post);
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
