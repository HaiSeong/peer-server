package com.nodam.server.repository;

import com.nodam.server.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {
    // db 연동 코드
    // JPA
    static public ArrayList<UserDTO> users;

    static {
        users = new ArrayList<>();
        users.add(new UserDTO("18010704", "19990808", "정해성", "010-5444-7857", false, "소프트웨어학과", false));
        users.add(new UserDTO("19010704", "20000301", "정해성", "010-1233-4444", true, "국문학과", false));
        users.add(new UserDTO("22010704", "1444", "정해성", "010-6666-8123", false, "전자정보통신공학과", false));
    }

    public UserDTO insertUser(UserDTO user){
        users.add(user);
        return user;
    }

    public ArrayList<UserDTO> getAllUsers(){
        return users;
    }

    public UserDTO getUserById(String id){
        return users.stream()
                .filter(userDTO -> userDTO.getId().equals(id))
                .findAny()
                .orElse(new UserDTO());
    }

    public void updateUserPw(String id, UserDTO userDTO) {
        users.stream()
                .filter(userDTO1 -> userDTO.getId().equals(id))
                .findAny()
                .orElse(new UserDTO())
                .setPassword(userDTO.getPassword());
    }

    public void deleteUser(String id) {
        users.removeIf(userDTO -> userDTO.getId().equals(id));
    }
}
