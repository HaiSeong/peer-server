package com.nodam.server.repository;

import com.nodam.server.dto.UserDTO;
import com.nodam.server.mapper.UserDTOMapper;
import com.nodam.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {
    @Autowired
    private UserDTOMapper mapper;

    public int insertUser(UserDTO user){
        if (mapper.getUserById(user.getId()) != null)
            return 0;
        mapper.insertUser(user);
        return 1;
    }


    public ArrayList<UserDTO> getAllUsers(){
        return mapper.getAllUsers();
    }

    public ArrayList<UserDTO> getFindingUsers(){
        return mapper.getFindingUsers();
    }

    public UserDTO getUserById(String id){
        if (mapper.getUserById(id) == null)
            return new UserDTO();
        return mapper.getUserById(id);
    }

    public int updateUser(String id, UserDTO userDTO) {
        if (mapper.getUserById(id) == null)
            return 0;
        mapper.updateUser(id, userDTO);
        return 1;
    }

    public int deleteUser(String id) {
        if (mapper.getUserById(id) == null)
            return 0;
        mapper.deleteUser(id);
        return 1;
    }
}
