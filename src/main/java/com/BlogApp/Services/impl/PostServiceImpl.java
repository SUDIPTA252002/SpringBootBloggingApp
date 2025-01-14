package com.BlogApp.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApp.Entities.Category;
import com.BlogApp.Entities.Post;
import com.BlogApp.Entities.User;
import com.BlogApp.Exceptions.ResourceNotFoundException;
import com.BlogApp.Payloads.CategoryDTO;
import com.BlogApp.Payloads.LikesDTO;
import com.BlogApp.Payloads.PostDTO;
import com.BlogApp.Payloads.UserDTO;
import com.BlogApp.Repositories.Mysql.CategoryRepo;
import com.BlogApp.Repositories.Mysql.PostRepo;
import com.BlogApp.Repositories.Mysql.UserRepo;
import com.BlogApp.Services.PostService;

@Service
public class PostServiceImpl implements PostService 
{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CategoryRepo catRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDTO createPost(PostDTO postDto,Integer categoryId,String userId)
    {
        Category category=this.catRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryID", Integer.toString(categoryId)));
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
        
        Post post=this.DtoToPost(postDto);
        post.setCategory(category);
        post.setUser(user);
        Post savedPost=this.postRepo.save(post);
        PostDTO createdPost=this.PostToDto(savedPost);
        createdPost.setCategoryDto(this.modelMapper.map(savedPost.getCategory(),CategoryDTO.class));
        createdPost.setUserDto(this.modelMapper.map(savedPost.getUser(), UserDTO.class));
        //createdPost.setLikesDto(this.modelMapper.map(savedPost.getLikes(),LikesDTO.class));
        return createdPost;
    }

    @Override
    public PostDTO getPost(Integer postId) 
    {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostID", Integer.toString(postId)));
        PostDTO postDto=this.PostToDto(post);
        postDto.setCategoryDto(this.modelMapper.map(post.getCategory(),CategoryDTO.class));
        postDto.setUserDto(this.modelMapper.map(post.getUser(),UserDTO.class));

        List<LikesDTO> likesDTOs=post.getLikes()
                                .stream()
                                .map(like->{LikesDTO likesDto=this.modelMapper.map(like,LikesDTO.class);
                                likesDto.setUserDTO(this.modelMapper.map(like.getUser(),UserDTO.class));
                                    return likesDto;
                                }
                                )
                                .collect(Collectors.toList());

        postDto.setLikesDto(likesDTOs);
        return postDto;
        
    }

    @Override
    public List<PostDTO> getAll() 
    {
        List<Post> posts=this.postRepo.findAll();
        List<PostDTO> postDtos=posts.stream().map(post->this.PostToDto(post)).collect(Collectors.toList());
        return postDtos;
    }
        
    @Override
    public PostDTO updatePost(PostDTO postDto, Integer postId) 
    {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostID", Integer.toString(postId)));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setMediaUrl(postDto.getMediaUrl());        
        
        Post updatedPost=this.postRepo.save(post);
        return this.PostToDto(updatedPost);
    }

    @Override
    public void deletePost(Integer postId) 
    {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostID", Integer.toString(postId)));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDTO> getPostByUser(String userId) 
    {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
        List<Post> posts=this.postRepo.findAllByUser(user);
        List<PostDTO> postDtos=posts.stream().map(post->this.PostToDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDTO> getPostByCategory(Integer categoryId) 
    {
        Category category=this.catRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",Integer.toString(categoryId)));
        List<Post> posts=this.postRepo.findAllByCategory(category);
        List<PostDTO> postDtos=posts.stream().map(post->this.PostToDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<Post> searchPost(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPost'");
    }


    private Post DtoToPost(PostDTO postDto)
    {
        Post post=this.modelMapper.map(postDto,Post.class);
        return post;
    }

    private PostDTO PostToDto(Post post)
    {
        return this.modelMapper.map(post, PostDTO.class);
    }

    
    
}
