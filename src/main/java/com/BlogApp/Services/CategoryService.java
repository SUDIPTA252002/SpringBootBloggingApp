package com.BlogApp.Services;

import java.util.List;

import com.BlogApp.Payloads.CategoryDTO;

public interface CategoryService 
{
    CategoryDTO getCatUsingId(Integer catId);
    
    List<CategoryDTO> getAll();
    
    CategoryDTO createCategory(CategoryDTO catDto);

    CategoryDTO updateCategory(CategoryDTO catDto,Integer catId);

    void deleteCategory(Integer catId);    
}
