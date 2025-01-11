package com.BlogApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogApp.Entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>
{

    
}
