package dev.personal.blog.errors;

public class PostDoesNotExistException extends RuntimeException{

    public PostDoesNotExistException(Long id){
        super("A post with id '%s' does not exist".formatted(id));
    }
}
