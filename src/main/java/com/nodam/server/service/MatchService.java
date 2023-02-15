package com.nodam.server.service;

import com.nodam.server.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class MatchService {
    @Autowired
    UserService userService;

    public ArrayList<UserDTO> getUsersFitConditions(UserDTO user, String purpose, String targetGender){
        ArrayList<UserDTO> findingUsers = userService.getFindingUsers();
        ArrayList<UserDTO> matchedList = new ArrayList<>();
        for (UserDTO otherUser : findingUsers){
            if (otherUser.getPurpose().equals(purpose))
                continue;

            if (targetGender.equals("MAIL") && otherUser.getTargetGender().equals("FEMAIL"))
                continue;
            else if (targetGender.equals("FEMAIL") && otherUser.getTargetGender().equals("MAIL"))
                continue;

            if (otherUser.getTargetBoundary().equals("COLLEGE") && !user.getCollege().equals(otherUser.getCollege()))
                continue;
            else if (otherUser.getTargetBoundary().equals("MAJOR") && !user.getMajor().equals(otherUser.getMajor()))
                continue;

            matchedList.add(otherUser);
        }

        return matchedList;
    }

    public ArrayList<UserDTO> getUsersFitConditions(UserDTO user, String purpose, String targetGender, String targetBoundary){
        ArrayList<UserDTO> findingUsers = userService.getFindingUsers();
        ArrayList<UserDTO> matchedList = new ArrayList<>();
        for (UserDTO otherUser : findingUsers){
            if (otherUser.getPurpose().equals(purpose))
                continue;

            if (targetGender.equals("MAIL") && otherUser.getTargetGender().equals("FEMAIL"))
                continue;
            else if (targetGender.equals("FEMAIL") && otherUser.getTargetGender().equals("MAIL"))
                continue;

            if (targetBoundary.equals("COLLEGE") && !otherUser.getCollege().equals(targetBoundary))
                continue;
            else if (targetBoundary.equals("MAJOR") && !otherUser.getMajor().equals(targetBoundary))
                continue;
            if (otherUser.getTargetBoundary().equals("COLLEGE") && !user.getCollege().equals(otherUser.getCollege()))
                continue;
            else if (otherUser.getTargetBoundary().equals("MAJOR") && !user.getMajor().equals(otherUser.getMajor()))
                continue;

            matchedList.add(otherUser);
        }

        return matchedList;
    }


    public Map<String, Integer> getPoolNumbers(String id, String purpose, String targetGender){
        int majorCnt = 0;
        int collegeCnt = 0;
        Map<String, Integer> map = new HashMap<>();

        ArrayList<UserDTO> userPool = getUsersFitConditions(userService.getUserById(id), purpose, targetGender);

        for (UserDTO otherUser : userPool) {
            if (otherUser.getTargetBoundary().equals("MAJOR")) {
                majorCnt++;
            } else if (otherUser.getTargetBoundary().equals("COLLEGE")) {
                majorCnt++;
                collegeCnt++;
            }
        }

        map.put("major", majorCnt);
        map.put("college", collegeCnt);
        map.put("all", userPool.size());

        return map;
    }


    public Map<String, String> getStatus(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("status", userService.getUserById(id).getStatus());
        return map;
    }


}
