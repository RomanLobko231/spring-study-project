package dev.personal.blog.controller;

import dev.personal.blog.model.BlogPost;
import dev.personal.blog.model.Email;
import dev.personal.blog.service.EmailService;
import dev.personal.blog.service.PostService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class PostsController {

    private final PostService postService;
    private final EmailService emailService;

    public PostsController(PostService postService, EmailService emailService) {
        this.postService = postService;
        this.emailService = emailService;
    }

    @GetMapping
    List<BlogPost> getAllPosts(){
        return postService.findAll();
    }

    @GetMapping
    @RequestMapping("/{id}")
    BlogPost getPostById(@PathVariable Long id){
        return postService.findById(id);
    }

    @PostMapping
    ResponseEntity<BlogPost> savePost(@RequestBody BlogPost post){
        //check if user is admin
        postService.savePost(post);
        String titleReformatted = post.getPostTitle().replace(" ", "-");
        return ResponseEntity.created(URI.create("http://localhost:8080/%s_%d".formatted(titleReformatted, post.getId()))).body(post);
    }

    @PostMapping
    @RequestMapping("/send-email")
    ResponseEntity<Email> sendEmail(@RequestBody Email email)  {
        /*
        emailService.sendSimpleMessage(email.getEmailBody(), email.getEmailSubject(), email.getRecipient());
        emailService.sendTemplateMessage(email);
        emailService.sendHtmlMessage(email.getRecipient(), email.getEmailSubject(), email.getEmailBody());

        */
        emailService.sendMessageWithAttachment(email.getRecipient(), email.getEmailSubject(), email.getEmailBody(), "");
        return ResponseEntity.ok(email);
    }

    @PutMapping
    ResponseEntity<BlogPost> updatePost(@RequestBody BlogPost post){
        //check if user is admin
        postService.savePost(post);
        String titleReformatted = post.getPostTitle().replace(" ", "-");
        return ResponseEntity.created(URI.create("http://localhost:8080/%s_%d".formatted(titleReformatted, post.getId()))).body(post);
    }

    @DeleteMapping
    ResponseEntity<BlogPost> deletePostById(@RequestParam(value = "deleteId") Long id){
        //check if user is admin
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }

}
