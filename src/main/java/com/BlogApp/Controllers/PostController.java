package com.BlogApp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.Payloads.ApiResponse;
import com.BlogApp.Payloads.PostDTO;
import com.BlogApp.Services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController
{
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/create-post")
    public ResponseEntity<PostDTO> createPost(@Valid@RequestBody PostDTO postDto,
                                              @PathVariable String userId,
                                              @PathVariable Integer categoryId)
    {

        PostDTO creatdePost=this.postService.createPost(postDto, categoryId, userId);
        return new ResponseEntity<PostDTO>(creatdePost,HttpStatus.OK);

    }

    @GetMapping("/get-post/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Integer postId)
    {
        PostDTO postDto=this.postService.getPost(postId);
        return new ResponseEntity<PostDTO>(postDto,HttpStatus.OK);

    }

    @GetMapping("/category/get-post/{categoryId}")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId)
    {
        List<PostDTO> postDTOs=this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
    }

    @GetMapping("/user/get-post/{userId}")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable String userId)
    {
        List<PostDTO> postDTOs=this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
    }

    @GetMapping("/get-all/")
    public ResponseEntity<List<PostDTO>> getAll()
    {
        List<PostDTO> postDTOs=this.postService.getAll();
        return new ResponseEntity<List<PostDTO>>(postDTOs,HttpStatus.OK);
    }

    @PutMapping("/update-post/{postId}")
    public ResponseEntity<PostDTO> updatePost(@Valid@RequestBody PostDTO postDTO,@PathVariable Integer postId)
    {
        PostDTO postDto=this.postService.updatePost(postDTO, postId);
        return new ResponseEntity<PostDTO>(postDto, HttpStatus.OK);
    }


    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
    {
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully",true), HttpStatus.OK);
    } 
}



