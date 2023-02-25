package com.nodam.server.service;

import com.nodam.server.dto.UserDTO;
import com.nodam.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public int insertUser(UserDTO user){
        return userRepository.insertUser(user);
    }

    public ArrayList<UserDTO> getAllUsers(){
        ArrayList<UserDTO> users = userRepository.getAllUsers();
        if (users.size() == 0)
            return new ArrayList<>();
        return users;
    }

    public ArrayList<UserDTO> getFindingUsers(){
        ArrayList<UserDTO> users = userRepository.getFindingUsers();
        if (users.size() == 0)
            return new ArrayList<>();
        return users;
    }

    public UserDTO getUserById(String id){
        return userRepository.getUserById(id);
    }

    public int updateUser(String id, UserDTO user){
        return userRepository.updateUser(id, user);
    }

    public int deleteUser(String id){
        return userRepository.deleteUser(id);
    }

    public boolean isUser(String id){
        return !userRepository.getUserById(id).getId().equals("");
    }
}
