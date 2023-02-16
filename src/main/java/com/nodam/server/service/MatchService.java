package com.nodam.server.service;

import com.nodam.server.dto.UserDTO;
import com.nodam.server.dto.matchDTO.MatchDTO2;
import com.nodam.server.dto.smsDTO.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

            if (targetGender.equals("MAIL") && otherUser.getGender().equals("FEMAIL"))
                continue;
            else if (targetGender.equals("FEMAIL") && otherUser.getGender().equals("MAIL"))
                continue;

            if (otherUser.getTargetBoundary().equals("COLLEGE") && !user.getCollege().equals(otherUser.getCollege()))
                continue;
            else if (otherUser.getTargetBoundary().equals("MAJOR") && !user.getMajor().equals(otherUser.getMajor()))
                continue;

            matchedList.add(otherUser);
        }

        return matchedList;
    }

    public ArrayList<UserDTO> getUserFitConditions(UserDTO user, String gender, String purpose, String targetGender, String targetBoundary){
        ArrayList<UserDTO> findingUsers = userService.getFindingUsers();
        ArrayList<UserDTO> matchedList = new ArrayList<>();
        for (UserDTO otherUser : findingUsers){
            if (otherUser.getPurpose().equals(purpose))
                continue;

            if (targetGender.equals("MAIL") && otherUser.getGender().equals("FEMAIL"))
                continue;
            else if (targetGender.equals("FEMAIL") && otherUser.getGender().equals("MAIL"))
                continue;

            if (targetBoundary.equals("MAJOR") && !otherUser.getMajor().equals(targetBoundary))
                continue;
            else if (targetBoundary.equals("COLLEGE") && !otherUser.getCollege().equals(targetBoundary))
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
        int majorCnt = 0;
        int collegeCnt = 0;
        Map<String, Integer> map = new HashMap<>();

        ArrayList<UserDTO> userPool = getUsersFitConditions(userService.getUserById(id), gender, purpose, targetGender);

        for (UserDTO otherUser : userPool) {
            if (otherUser.getTargetBoundary().equals("MAJOR")) {
                majorCnt++;
                collegeCnt++;
            } else if (otherUser.getTargetBoundary().equals("COLLEGE")) {
                collegeCnt++;
            }
        }

        map.put("major", majorCnt);
        map.put("college", collegeCnt);
        map.put("all", userPool.size());

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
        ArrayList<UserDTO> userPool = getUserFitConditions(user, user.getGender(), user.getPurpose(), user.getTargetGender(), user.getTargetBoundary());
        if (userPool.size() == 0) { // 바로 불가능한 경우
            user.setFinding(true);
            user.setStatus("ON_GOING");
            userService.updateUser(id, user);
        }
        else { // 바로 가능한 경우
            UserDTO partner = userPool.get(0);
            user.setFinding(false); partner.setFinding(false);
            user.setStatus("DONE"); partner.setStatus("DONE");
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


    public Map<String, String> getStatus(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("status", userService.getUserById(id).getStatus());
        return map;
    }

    public void breakRelationship(String id){
        UserDTO userDTO = userService.getUserById(id);
        userDTO.setPartnerId(null);
        userDTO.setStatus("NOT_REGISTER");
        userDTO.setPhoneNumber(null);
        userDTO.setPurpose(null);
        userDTO.setTargetGender(null);
        userDTO.setTargetBoundary(null);
        userService.updateUser(id, userDTO);
    }
}
