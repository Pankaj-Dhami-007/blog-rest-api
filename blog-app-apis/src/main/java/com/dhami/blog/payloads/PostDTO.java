package com.dhami.blog.payloads;

import com.dhami.blog.entity.Category;
import com.dhami.blog.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDTO {
    private Long postId;
    private String title;
    private String content;
    private String imageName;
    private LocalDateTime createdAt;

    private CategoryDTO category;
    private UserDTO user;


}
