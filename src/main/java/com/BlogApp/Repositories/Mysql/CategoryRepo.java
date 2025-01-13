package com.BlogApp.Repositories.Mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogApp.Entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>
{

    
}
