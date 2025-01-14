package com.BlogApp.Services.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApp.Entities.Likes;
import com.BlogApp.Entities.Post;
import com.BlogApp.Entities.User;
import com.BlogApp.Exceptions.AlreadyLikedException;
import com.BlogApp.Exceptions.ResourceNotFoundException;
import com.BlogApp.Payloads.LikesDTO;
import com.BlogApp.Repositories.Mysql.LikesRepo;
import com.BlogApp.Repositories.Mysql.PostRepo;
import com.BlogApp.Repositories.Mysql.UserRepo;
import com.BlogApp.Services.LikesService;

@Service
public class LikesServiceImpl implements LikesService 
{

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private LikesRepo likesRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void likePost(String userId, Integer postId) 
    {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", Integer.toString(postId)));

        if(likesRepo.existsByUserAndPost(user,post))
        {
            throw new AlreadyLikedException(Integer.toString(postId), "User already liked the post");
        }
        Likes like = new Likes();
        like.setUser(user);
        like.setPost(post);
        likesRepo.save(like);
    }

    @Override
    public void unlikePost(String userId, Integer postId) 
    {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", Integer.toString(postId)));
        Likes like=this.likesRepo.findByUserAndPost(user, post).orElseThrow(()-> new ResourceNotFoundException("Like of User", "postId", Integer.toString(postId)));
        this.likesRepo.delete(like);
    }

    @Override
    public long likesCount(Integer postId) 
    {

        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", Integer.toString(postId)));
        long count=this.likesRepo.countByPost(post);
        return count;
    }

    private LikesDTO LikesToDto(Likes like)
    {
        return this.modelMapper.map(like,LikesDTO.class);

    }
    private Likes DTOtoLikes(LikesDTO likesDto)
    {
        return this.modelMapper.map(likesDto,Likes.class);
    }
    
}
