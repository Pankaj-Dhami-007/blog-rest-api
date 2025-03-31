package com.dhami.blog.service;

import com.dhami.blog.entity.Category;
import com.dhami.blog.entity.Post;
import com.dhami.blog.entity.User;
import com.dhami.blog.exceptions.ResourceNotFoundException;
import com.dhami.blog.payloads.PostDTO;
import com.dhami.blog.repository.CategoryRepository;
import com.dhami.blog.repository.PostRepository;
import com.dhami.blog.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public PostDTO createPost(PostDTO postDTO, Long userId, Long categoryId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId", categoryId));



        Post post = modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setCreatedAt(LocalDateTime.now());

        post.setUser(user);
        post.setCategory(category);

        Post newPost = postRepository.save(post);

        return modelMapper.map(newPost, PostDTO.class);

    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long postId) {
        Post post =  postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post","postId", postId));

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());

        Post updatedPost = postRepository.save(post);

        return modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public void deletePost(Long postId) {

       Post post =  postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post","postId", postId));
       postRepository.delete(post);
    }

    @Override
    public List<PostDTO> getAllposts() {
        List<Post> allPosts = postRepository.findAll();
        List<PostDTO> postDTOS = allPosts.stream().map(post-> modelMapper.map(post,PostDTO.class)).toList();

        return postDTOS;
    }

    @Override
    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostsByCategory(Long category_Id) {
        Category category = categoryRepository.findById(category_Id)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId", category_Id));
       List<Post> posts =  postRepository.findByCategory(category);

       return posts.stream().map(post-> modelMapper.map(post, PostDTO.class)).toList();
    }

    @Override
    public List<PostDTO> getAllPostByUser(Long userId) {

       User userInDb =  userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));

       List<Post> postsInDb = postRepository.findByUser(userInDb);

        postsInDb.forEach(post -> System.out.println("Post: " + post.getTitle()));
        List<PostDTO> postDTOS =postsInDb.stream().map(post-> modelMapper.map(post, PostDTO.class)).toList();
        return postDTOS;
    }

    @Override
    public List<Post> searchPost(String keyword) {
        return List.of();
    }
}
