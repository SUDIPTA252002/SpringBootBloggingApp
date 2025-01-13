package com.BlogApp.Services;

import java.util.List;

import com.BlogApp.Entities.Post;
import com.BlogApp.Payloads.PostDTO;

public interface PostService
{
    PostDTO createPost(PostDTO postDto,Integer categoryId,String userId);

    PostDTO getPost(Integer postId);

    List<PostDTO> getAll();

    PostDTO updatePost(PostDTO postDto,Integer postId);

    void deletePost(Integer postId);

    List<PostDTO> getPostByUser(String userId);

    List<PostDTO> getPostByCategory(Integer categoryId);

    List<Post> searchPost(String keyword);
    
}
