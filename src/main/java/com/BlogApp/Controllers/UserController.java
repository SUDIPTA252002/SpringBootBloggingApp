package com.BlogApp.Controllers;

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
import com.BlogApp.Payloads.UserDTO;
import com.BlogApp.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController 
{
    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@Valid@RequestBody UserDTO userDto)
    {  
            UserDTO CreatedUser=this.userService.createUser(userDto);
            return new ResponseEntity<>(CreatedUser,HttpStatus.CREATED);
        // catch(Exception e)
        // {
        //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // }   
    }
    
    @GetMapping("/get-user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId)
    {       
        UserDTO userDto=this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<?> updateUser(@Valid@RequestBody UserDTO userDto,@PathVariable("userId") String userId)
    { 
        try
        {
            UserDTO updatedUser=this.userService.updateUser(userDto, userId);
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
            
        } 
        catch (Exception e)
        {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId)
    {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Succesfully",true),HttpStatus.OK);
    }
    
}
