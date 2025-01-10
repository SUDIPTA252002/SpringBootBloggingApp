package com.BlogApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApp.Entities.User;

public interface UserRepo extends JpaRepository<User,String>
{

    
} 
