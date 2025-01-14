package com.BlogApp.Services;

public interface LikesService 
{
     void likePost(String userId,Integer postId);

     void unlikePost(String userId,Integer postId);

     long likesCount(Integer postId);
    
}
