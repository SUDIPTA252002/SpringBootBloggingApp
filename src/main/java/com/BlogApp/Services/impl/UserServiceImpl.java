package com.BlogApp.Services.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApp.Entities.User;
import com.BlogApp.Payloads.UserDTO;
import com.BlogApp.Repositories.UserRepo;
import com.BlogApp.Services.UserService;
import com.BlogApp.Exceptions.*;

@Service
public class UserServiceImpl implements UserService 
{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDto) 
    {
        User user=this.DTOtoUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.UsertoDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto,String userId) 
    {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setProfilePicture(userDto.getProfilePicture());

        User updatedUser=this.userRepo.save(user);
        UserDTO userDtoUpdated=this.UsertoDTO(updatedUser);
        return userDtoUpdated;
    }

    @Override
    public UserDTO getUserById(String userId) 
    {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        UserDTO userDto=this.UsertoDTO(user);
        return userDto;
    }

    @Override
    public UserDTO getUserByName(String firstName, String lastName) 
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserByName'");
    }

    @Override
    public List<UserDTO> getAllUsers() 
    {
        List<User> usersList=this.userRepo.findAll();
        List<UserDTO> userDtos=usersList.stream().map(user->this.UsertoDTO(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(String userID) 
    {
        User user=this.userRepo.findById(userID).orElseThrow(()->new ResourceNotFoundException("User","Id",userID));
        this.userRepo.delete(user);
    }

    private User DTOtoUser(UserDTO userDto)
    {
        User user=modelMapper.map(userDto,User.class);
        user.setUserId(generateUserId(userDto.getFirstName(), userDto.getLastName()));
        return user;

    }
    
    private String generateUserId(String firstName,String lastName)
    {
        String initials=firstName.substring(0,1)+lastName.substring(0, 1);
        Random random=new Random();
        int randomNumber=1000+random.nextInt(9000);
        return initials+Integer.toString(randomNumber);
    }

    private UserDTO UsertoDTO(User user)
    {
        UserDTO userDTO = modelMapper.map(user,UserDTO.class); 
        return userDTO;

    }
}
