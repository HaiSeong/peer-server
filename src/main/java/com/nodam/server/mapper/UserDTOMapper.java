package com.nodam.server.mapper;

import com.nodam.server.dto.UserDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Mapper
public interface UserDTOMapper {

        @Insert("INSERT INTO `nodam_db`.`UserDTO` (`id`, `password`, `name`, `phoneNumber`, `gender`, `major`, `studentNumber`, `grade`, `role`, `finding`) VALUES (#{id}, #{password}, #{name}, #{phoneNumber}, #{gender}, #{major}, #{studentNumber}, #{grade}, #{role}, #{finding})")
        public int insertUser(@RequestBody UserDTO user);

        @Select("SELECT * FROM UserDTO;")
        public ArrayList<UserDTO> getAllUsers();

        @Select("SELECT * FROM UserDTO WHERE id=#{id};")
        public UserDTO getUserById(@Param("id") String id);

        @Update("UPDATE `nodam_db`.`UserDTO` SET `password` = #{userDTO.password}, `name` = #{userDTO.name}, `phoneNumber` = #{userDTO.phoneNumber}, `gender` = #{userDTO.gender}, `major` = #{userDTO.major}, `studentNumber` = #{userDTO.studentNumber}, `grade` = #{userDTO.grade}, `role` = #{userDTO.role}, `finding` = #{userDTO.finding} WHERE (`id` = #{id});")
        public int updateUser(@Param("id") String id, @RequestBody UserDTO userDTO);

        @Delete("DELETE FROM `nodam_db`.`UserDTO` WHERE (`id` = #{id});")
        public int deleteUser(@Param("id") String id);

}
