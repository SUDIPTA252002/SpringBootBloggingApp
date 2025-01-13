package com.BlogApp.Repositories.Mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogApp.Entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,String>
{

    
} 
