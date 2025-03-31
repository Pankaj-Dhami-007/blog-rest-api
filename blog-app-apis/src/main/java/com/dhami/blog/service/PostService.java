package com.dhami.blog.service;

import com.dhami.blog.entity.Category;
import com.dhami.blog.entity.Post;
import com.dhami.blog.entity.User;
import com.dhami.blog.payloads.PostDTO;

import java.util.List;

public interface PostService{
    // create
   PostDTO createPost(PostDTO postDTO, Long userId, Long categoryId);

   //Update

   PostDTO updatePost(PostDTO postDTO, Long id);

   //delete

    void deletePost(Long postId);

    // get all post

    List<PostDTO> getAllposts();


    // get post by id
   PostDTO getPostById(Long postId);
   // get post by category id
    List<PostDTO> getPostsByCategory(Long category_Id);

    // get All post by user

    List<PostDTO>getAllPostByUser(Long userId);

   List<Post> searchPost(String keyword);

}
