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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int insertUser(UserDTO user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        passwordEncoder.matches() // 패스워드 비교
        return userRepository.insertUser(user);
    }

    public ArrayList<UserDTO> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public UserDTO getUserById(String id){
        return userRepository.getUserById(id);
    }

    public int updateUser(String id, UserDTO user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.updateUserPw(id, user);
    }

    public int deleteUser(String id){
        return userRepository.deleteUser(id);
    }
}
