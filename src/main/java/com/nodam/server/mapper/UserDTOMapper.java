package com.nodam.server.mapper;

import com.nodam.server.dto.UserDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Mapper
public interface UserDTOMapper {

        @Insert("INSERT INTO `nodam_db`.`UserDTO` (`id`, `name`, `major`, `college`, `studentNumber`, `grade`, `finding`, `state`, `yellowCard`) VALUES (#{id}, #{name}, #{major}, #{college}, #{studentNumber}, #{grade}, #{finding}, #{state}, #{yellowCard})")
        public int insertUser(@RequestBody UserDTO user);

        @Select("SELECT * FROM UserDTO;")
        public ArrayList<UserDTO> getAllUsers();

        @Select("SELECT * FROM UserDTO WHERE finding=1 ORDER BY searchStart;")
        public ArrayList<UserDTO> getFindingUsers();

        @Select("SELECT * FROM UserDTO WHERE id=#{id};")
        public UserDTO getUserById(@Param("id") String id);

        @Update("UPDATE `nodam_db`.`UserDTO` SET `name` = #{userDTO.name}, `major` = #{userDTO.major}, `college` = #{userDTO.college}, `studentNumber` = #{userDTO.studentNumber}, `grade` = #{userDTO.grade}, `finding` = #{userDTO.finding}, `state` = #{userDTO.state}, `yellowCard` = #{userDTO.yellowCard}, `gender` = #{userDTO.gender}, `phoneNumber` = #{userDTO.phoneNumber}, `kakaoId` = #{userDTO.kakaoId}, `purpose` = #{userDTO.purpose}, `targetGender` = #{userDTO.targetGender}, `gradeLimit` = #{userDTO.gradeLimit}, `studentNumberLimit` = #{userDTO.studentNumberLimit}, `targetBoundary` = #{userDTO.targetBoundary}, `searchStart` = #{userDTO.searchStart}, `partnerId` = #{userDTO.partnerId}, `matchedTime` = #{userDTO.matchedTime}, `unblockTime` = #{userDTO.unblockTime} WHERE `id` = #{id}; ")
        public int updateUser(@Param("id") String id, UserDTO userDTO);

        @Delete("DELETE FROM `nodam_db`.`UserDTO` WHERE (`id` = #{id});")
        public int deleteUser(@Param("id") String id);

}
