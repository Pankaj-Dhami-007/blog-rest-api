package com.dhami.blog.controller;
import com.dhami.blog.payloads.ApiResponse;
import com.dhami.blog.payloads.PostDTO;
import com.dhami.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,
                                              @PathVariable Long userId,
                                              @PathVariable Long categoryId){
     PostDTO createPost = postService.createPost(postDTO, userId, categoryId);

     return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    //get by user

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Long userId){
        List<PostDTO> posts = postService.getAllPostByUser(userId);

        return new ResponseEntity<>(posts, HttpStatus.OK);

    }

    // get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Long categoryId){
        List<PostDTO> posts = postService.getAllPostByUser(categoryId);

        return new ResponseEntity<>(posts, HttpStatus.OK);

    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAll(){
        List<PostDTO> posts = postService.getAllposts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId){
        PostDTO post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ApiResponse deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return new ApiResponse("post is succesfully deleted! ", true);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Long postId){
        PostDTO updatedPost = postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }
}
