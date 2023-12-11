package dev.personal.blog.service;

import dev.personal.blog.errors.PostDoesNotExistException;
import dev.personal.blog.model.BlogPost;
import dev.personal.blog.repository.PostsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostsRepository repository;

    public PostService(PostsRepository repository) {
        this.repository = repository;
    }

    public List<BlogPost> findAll(){
        return repository.findAll();
    }

    public BlogPost findById(Long id){
        return repository.findById(id).orElseThrow(() -> new PostDoesNotExistException(id));
    }

    public void savePost(BlogPost post){
        repository.save(post);
    }

    public void deletePostById(Long id){
        repository.deleteById(id);
    }
}
