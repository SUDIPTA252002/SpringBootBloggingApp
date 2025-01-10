package com.BlogApp.Services;

import java.util.List;

import com.BlogApp.Payloads.UserDTO;

public interface UserService 
{
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO userDto,String userId);
    UserDTO getUserById(String userId);
    UserDTO getUserByName(String firstName,String lastName);
    List<UserDTO> getAllUsers();
    void deleteUser(String userID);
}
