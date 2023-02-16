package com.nodam.server.mapper;

import com.nodam.server.dto.UserDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Mapper
public interface UserDTOMapper {

        @Insert("INSERT INTO `nodam_db`.`UserDTO` (`id`, `password`, `name`, `major`, `college`, `studentNumber`, `grade`, `finding`, `status`) VALUES (#{id}, #{password}, #{name}, #{major}, #{college}, #{studentNumber}, #{grade}, #{finding}, #{status})")
        public int insertUser(@RequestBody UserDTO user);

        @Select("SELECT * FROM UserDTO;")
        public ArrayList<UserDTO> getAllUsers();

        @Select("SELECT * FROM UserDTO WHERE finding=1 ORDER BY searchStart;")
        public ArrayList<UserDTO> getFindingUsers();

        @Select("SELECT * FROM UserDTO WHERE id=#{id};")
        public UserDTO getUserById(@Param("id") String id);

        @Update("UPDATE `nodam_db`.`UserDTO` SET `password` = #{userDTO.password}, `name` = #{userDTO.name}, `major` = #{userDTO.major}, `college` = #{userDTO.college}, `studentNumber` = #{userDTO.studentNumber}, `grade` = #{userDTO.grade}, `finding` = #{userDTO.finding}, `status` = #{userDTO.status}, `gender` = #{userDTO.gender}, `phoneNumber` = #{userDTO.phoneNumber}, `purpose` = #{userDTO.purpose}, `targetGender` = #{userDTO.targetGender}, `targetBoundary` = #{userDTO.targetBoundary}, `searchStart` = #{userDTO.searchStart}, `partnerId` = #{userDTO.partnerId} WHERE `id` = #{id}; ")
        public int updateUser(@Param("id") String id, UserDTO userDTO);

        @Delete("DELETE FROM `nodam_db`.`UserDTO` WHERE (`id` = #{id});")
        public int deleteUser(@Param("id") String id);

}
