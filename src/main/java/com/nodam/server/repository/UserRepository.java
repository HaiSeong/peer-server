package com.nodam.server.repository;

import com.nodam.server.dto.UserDTO;
import com.nodam.server.mapper.UserDTOMapper;
import com.nodam.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {
    // db 연동 코드
    // JPA
    @Autowired
    private UserDTOMapper mapper;

//    public UserRepository(UserDTOMapper mapper){
//        this.mapper = mapper;
//    }

//    static public ArrayList<UserDTO> users;
//
//    static {
//        users = new ArrayList<>();
//        users.add(new UserDTO("18010704", "19990808", "정해성", "010-5444-7857", false, "소프트웨어학과", false));
//        users.add(new UserDTO("19010704", "20000301", "정해성", "010-1233-4444", true, "국문학과", false));
//        users.add(new UserDTO("22010704", "1444", "정해성", "010-6666-8123", false, "전자정보통신공학과", false));
//    }

    public int insertUser(UserDTO user){
//        users.add(user);
        if (mapper.getUserById(user.getId()) != null)
            return 0;
        mapper.insertUser(user);
        return 1;
    }

    public ArrayList<UserDTO> getAllUsers(){
//        return users;
        return mapper.getAllUsers();
    }

    public UserDTO getUserById(String id){
//        return users.stream()
//                .filter(userDTO -> userDTO.getId().equals(id))
//                .findAny()
//                .orElse(new UserDTO());
        if (mapper.getUserById(id) == null)
            return new UserDTO();
        return mapper.getUserById(id);
    }

    public int updateUserPw(String id, UserDTO userDTO) {
//        users.stream()
//                .filter(userDTO1 -> userDTO.getId().equals(id))
//                .findAny()
//                .orElse(new UserDTO())
//                .setPassword(userDTO.getPassword());
        if (mapper.getUserById(id) == null)
            return 0;
        mapper.updateUser(id, userDTO);
        return 1;
    }

    public int deleteUser(String id) {
//        users.removeIf(userDTO -> userDTO.getId().equals(id));
        if (mapper.getUserById(id) == null)
            return 0;
        mapper.deleteUser(id);
        return 1;
    }
}
