package com.nodam.server.service;

import com.nodam.server.dto.UserDTO;
import com.nodam.server.dto.matchDTO.MatchDTO2;
import com.nodam.server.dto.smsDTO.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class MatchService {
    @Autowired
    UserService userService;

    @Autowired
    SmsService smsService;

    public ArrayList<UserDTO> getUsersFitConditions(UserDTO user, String gender, String purpose, String targetGender){
        ArrayList<UserDTO> findingUsers = userService.getFindingUsers();
        ArrayList<UserDTO> matchedList = new ArrayList<>();
        for (UserDTO otherUser : findingUsers){
            if (otherUser.getPurpose().equals(purpose))
                continue;

            if (targetGender.equals("MALE") && otherUser.getGender().equals("FEMALE"))
                continue;
            else if (targetGender.equals("FEMALE") && otherUser.getGender().equals("MALE"))
                continue;
            if (otherUser.getTargetGender().equals("MALE") && gender.equals("FEMALE"))
                continue;
            else if (otherUser.getTargetGender().equals("FEMALE") && gender.equals("MALE"))
                continue;

            if (otherUser.getTargetBoundary().equals("COLLEGE") && !user.getCollege().equals(otherUser.getCollege()))
                continue;
            else if (otherUser.getTargetBoundary().equals("MAJOR") && !user.getMajor().equals(otherUser.getMajor()))
                continue;

            matchedList.add(otherUser);
        }

        return matchedList;
    }

    public ArrayList<UserDTO> getUsersFitConditions(UserDTO user, String gender, String purpose, String targetGender, String targetBoundary){
        ArrayList<UserDTO> findingUsers = userService.getFindingUsers();
        ArrayList<UserDTO> matchedList = new ArrayList<>();
        for (UserDTO otherUser : findingUsers){
            if (otherUser.getPurpose().equals(purpose))
                continue;

            if (targetGender.equals("MALE") && otherUser.getGender().equals("FEMALE"))
                continue;
            else if (targetGender.equals("FEMALE") && otherUser.getGender().equals("MALE"))
                continue;
            if (otherUser.getTargetGender().equals("MALE") && gender.equals("FEMALE"))
                continue;
            else if (otherUser.getTargetGender().equals("FEMALE") && gender.equals("MALE"))
                continue;

            if (targetBoundary.equals("MAJOR") && !user.getMajor().equals(otherUser.getMajor()))
                continue;
            else if (targetBoundary.equals("COLLEGE") && !user.getCollege().equals(otherUser.getCollege()))
                continue;
            if (otherUser.getTargetBoundary().equals("MAJOR") && !user.getMajor().equals(otherUser.getMajor()))
                continue;
            else if (otherUser.getTargetBoundary().equals("COLLEGE") && !user.getCollege().equals(otherUser.getCollege()))
                continue;

            matchedList.add(otherUser);
        }

        return matchedList;
    }


    public Map<String, Integer> getPoolNumbers(String id, String gender, String purpose, String targetGender){
        Map<String, Integer> map = new HashMap<>();

        ArrayList<UserDTO> allUserPool = getUsersFitConditions(userService.getUserById(id), gender, purpose, targetGender);
        ArrayList<UserDTO> collegeUserPool = getUsersFitConditions(userService.getUserById(id), gender, purpose, targetGender, "COLLEGE");
        ArrayList<UserDTO> majorUserPool = getUsersFitConditions(userService.getUserById(id), gender, purpose, targetGender, "MAJOR");

        map.put("major", majorUserPool.size());
        map.put("college", collegeUserPool.size());
        map.put("all", allUserPool.size());

        return map;
    }

    public void match(String id, MatchDTO2 matchDTO) throws Exception{
        UserDTO user = userService.getUserById(id);
        user.setGender(matchDTO.getGender());
        user.setPhoneNumber(matchDTO.getPhoneNumber());
        user.setPurpose(matchDTO.getPurpose());
        user.setTargetGender(matchDTO.getTargetGender());
        user.setTargetBoundary(matchDTO.getTargetBoundary());
        user.setSearchStart(LocalDateTime.now());
        ArrayList<UserDTO> userPool = getUsersFitConditions(user, user.getGender(), user.getPurpose(), user.getTargetGender(), user.getTargetBoundary());
        if (userPool.size() == 0) { // 바로 불가능한 경우
            user.setFinding(true);
            user.setState("ON_GOING");
            userService.updateUser(id, user);
        }
        else { // 바로 가능한 경우
            UserDTO partner = userPool.get(0);
            user.setFinding(false); partner.setFinding(false);
            user.setState("DONE"); partner.setState("DONE");
            user.setPartnerId(partner.getId()); partner.setPartnerId(user.getId());

            userService.updateUser(partner.getId(), partner);
            userService.updateUser(id, user);

            try {
                smsService.sendSms(new MessageDTO(matchDTO.getPhoneNumber(), partner.getName() + "\n" + partner.getPhoneNumber()));
                smsService.sendSms(new MessageDTO(partner.getPhoneNumber(), user.getName() + "\n" + user.getPhoneNumber()));
            }
            catch (Exception e){
                throw new Exception("fail to send sms");
            }
        }
    }


    public Map<String, String> getState(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("state", userService.getUserById(id).getState());
        return map;
    }

    public void breakRelationship(String id){
        UserDTO user = userService.getUserById(id);
        UserDTO partner = userService.getUserById(user.getPartnerId());

        System.out.println(user.toString());
        System.out.println(partner.toString());

        user.setPartnerId(null); partner.setPartnerId(null);
        user.setState("NOT_REGISTER"); partner.setState("NOT_REGISTER");
        user.setPurpose(null); partner.setPurpose(null);
        user.setTargetGender(null); partner.setTargetGender(null);
        user.setTargetBoundary(null); partner.setTargetBoundary(null);
        user.setSearchStart(null); partner.setSearchStart(null);
        userService.updateUser(id, user);
        userService.updateUser(partner.getId(), partner);
    }
}
