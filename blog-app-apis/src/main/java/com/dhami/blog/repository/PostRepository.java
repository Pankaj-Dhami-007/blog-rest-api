package com.dhami.blog.repository;

import com.dhami.blog.entity.Category;
import com.dhami.blog.entity.Post;
import com.dhami.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
