package dev.personal.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Entity
@Table(name= "blogposts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPost extends RepresentationModel<BlogPost> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("title")
    private String postTitle;

    @JsonProperty("content")
    @Column(nullable = false)
    private String postContent;

    @Column(nullable = false)
    @JsonProperty("dateCreated")
    Long dateCreated;
    @JsonProperty("dateUpdated")
    @Column()
    Long dateUpdated;

    public BlogPost(Long id, String postTitle, String postContent, Long dateCreated, Long dateUpdated) {
        this.id = id;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    protected BlogPost() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Long dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
