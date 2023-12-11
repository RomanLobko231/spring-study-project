package dev.personal.blog.errors.advice;

import dev.personal.blog.errors.PostDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PostDoesNotExistAdvice {

    @ResponseBody
    @ExceptionHandler(PostDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String postDoesNotExistHandler(PostDoesNotExistException exception){
        return exception.getMessage();
    }
}
