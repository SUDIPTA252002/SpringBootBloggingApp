package com.BlogApp.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApp.Entities.Category;
import com.BlogApp.Exceptions.ResourceNotFoundException;
import com.BlogApp.Payloads.CategoryDTO;
import com.BlogApp.Repositories.Mysql.CategoryRepo;
import com.BlogApp.Services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService
{

    @Autowired
    private CategoryRepo catRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO getCatUsingId(Integer catId)
    {
            Category cat=this.catRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","ID", Integer.toString(catId)));
            return this.CategoryToDTO(cat);
    }

    @Override
    public List<CategoryDTO> getAll()
    {
        List<CategoryDTO> catDtos=this.catRepo.findAll().stream().map(cat->this.CategoryToDTO(cat)).collect(Collectors.toList());
        return catDtos;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO catDto) 
    {
        Category cat=this.DTOtoCategory(catDto);
        Category savedCat=this.catRepo.save(cat);
        return this.CategoryToDTO(savedCat);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO catDto, Integer catId) 
    {
        Category cat=this.catRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", Integer.toString(catId)));
        cat.setCategoryTitle(catDto.getCategoryTitle());
        cat.setCategoryDescription(catDto.getCategoryDescription());
        cat.setSlug(catDto.getSlug());

        Category updatedCat=this.catRepo.save(cat);
        return this.CategoryToDTO(updatedCat);
    }

    @Override
    public void deleteCategory(Integer catId) 
    {
        Category cat=this.catRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", Integer.toString(catId)));
        this.catRepo.delete(cat);
    }


    private Category DTOtoCategory(CategoryDTO catDto)
    {
        Category cat=modelMapper.map(catDto,Category.class);
        return cat;
    }

    private CategoryDTO CategoryToDTO(Category cat)
    {
        CategoryDTO catDto=modelMapper.map(cat,CategoryDTO.class);
        return catDto;
    }
    
}
