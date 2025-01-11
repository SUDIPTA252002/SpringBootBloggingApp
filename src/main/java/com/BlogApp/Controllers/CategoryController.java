package com.BlogApp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.Payloads.ApiResponse;
import com.BlogApp.Payloads.CategoryDTO;
import com.BlogApp.Services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController
{
    @Autowired
    private CategoryService catService;

    @GetMapping("/get-category/{catId}")
    public ResponseEntity<?> getCategory(@PathVariable Integer catId)
    {
        CategoryDTO catDto=this.catService.getCatUsingId(catId);
        return new ResponseEntity<>(catDto,HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll()
    {
        List<CategoryDTO> catDtos=this.catService.getAll();
        return new ResponseEntity<>(catDtos,HttpStatus.OK);
    }

    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@Valid@RequestBody CategoryDTO catDto)
    {
        CategoryDTO created=this.catService.createCategory(catDto);
        return new ResponseEntity<>(created,HttpStatus.OK);
    }

    @PutMapping("/update-categrory")
    public ResponseEntity<?> updateCategory(@Valid@RequestBody CategoryDTO catDto,@PathVariable Integer catId)
    {
        CategoryDTO update=this.catService.updateCategory(catDto, catId);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/delete-category/{cat-Id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer catId)
    {
        this.catService.deleteCategory(catId);
        return new ResponseEntity<>(new ApiResponse("Categroy Deleted Successfully",true), HttpStatus.OK);
    }

    
}
