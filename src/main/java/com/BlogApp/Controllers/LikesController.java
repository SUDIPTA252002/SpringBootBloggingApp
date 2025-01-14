package com.BlogApp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.Payloads.ApiResponse;
import com.BlogApp.Services.LikesService;

@RestController
@RequestMapping("/api/likes")
public class LikesController 
{
    @Autowired
    private LikesService likesService;

    @PostMapping("/like-post/{userId}/{postId}")
    public ResponseEntity<?> likePost(@PathVariable String userId,
                                      @PathVariable Integer postId)
    {
        this.likesService.likePost(userId, postId);
        return new ResponseEntity<>(new ApiResponse("User liked the post",true),HttpStatus.OK);
    }

    @DeleteMapping("/unlike-post/{userId}/{postId}")
    public ResponseEntity<?> unlikePost(@PathVariable String userId,
                                        @PathVariable Integer postId)
    {
        this.likesService.unlikePost(userId, postId);
        return new ResponseEntity<>(new ApiResponse("User unliked the post",true),HttpStatus.OK);
    }

    @GetMapping("/count-like/{postId}")
    public ResponseEntity<Long> countLikes(@PathVariable Integer postId)
    {
        long count=this.likesService.likesCount(postId);
        return new ResponseEntity<>(count,HttpStatus.OK);
    }
    
}
