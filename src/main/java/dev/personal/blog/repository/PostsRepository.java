package dev.personal.blog.repository;

import dev.personal.blog.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<BlogPost, Long> {
}
