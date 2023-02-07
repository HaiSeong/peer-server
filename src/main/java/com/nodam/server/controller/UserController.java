package com.nodam.server.controller;

import com.nodam.server.dto.UserDTO;
import com.nodam.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("")
    public int insertUser(@RequestBody UserDTO user){
        return userService.insertUser(user);
    }

    @GetMapping("")
    public ArrayList<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public int updateUserPw(@PathVariable String id, @RequestBody UserDTO user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable String id){
        return userService.deleteUser(id);
    }
}
