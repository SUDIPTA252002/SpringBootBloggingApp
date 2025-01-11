package com.BlogApp.Payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO
{

    private Integer categoryId;

    @NotBlank(message = "Category Title should not be blank")
    private String categoryTitle;

    @NotBlank(message = "Category Description should not be blank")
    private String categoryDescription;

    @NotBlank(message = "Category Slug should not be blank")
    private String slug;
    
}
