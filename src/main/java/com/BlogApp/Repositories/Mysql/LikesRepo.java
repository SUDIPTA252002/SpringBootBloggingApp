package com.BlogApp.Repositories.Mysql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogApp.Entities.Likes;
import com.BlogApp.Entities.Post;
import com.BlogApp.Entities.User;

@Repository
public interface LikesRepo extends JpaRepository<Likes,Integer> 
{
    long countByPost(Post post);
    boolean existsByUserAndPost(User user,Post post);
    Optional<Likes> findByUserAndPost(User user,Post post);
    
}
