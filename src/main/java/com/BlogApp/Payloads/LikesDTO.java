package com.BlogApp.Payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikesDTO 
{
    private Integer likeId;

    private UserDTO userDTO;
    
}
