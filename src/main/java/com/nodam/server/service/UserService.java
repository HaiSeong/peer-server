package com.nodam.server.service;

import com.nodam.server.dto.UserDTO;
import com.nodam.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDTO insertUser(UserDTO user){
        return userRepository.insertUser(user);
    }

    public ArrayList<UserDTO> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public UserDTO getUserById(String id){
        return userRepository.getUserById(id);
    }

    public void updateUserPw(String id, UserDTO user){
        userRepository.updateUserPw(id, user);
    }

    public void deleteUser(String id){
        userRepository.deleteUser(id);
    }
}
