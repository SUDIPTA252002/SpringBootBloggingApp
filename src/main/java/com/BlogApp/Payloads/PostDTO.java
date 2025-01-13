package com.BlogApp.Payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO 
{

    private Integer postId;

    @NotBlank(message = "Title should not be null")
    private String title;

    @NotBlank(message = "Content should not be null")
    private String content;

    private String mediaUrl;

    private CategoryDTO categoryDto;

    private UserDTO userDto;
    
}
