package com.nodam.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nodam.server.controller.AuthController;
import com.nodam.server.dto.UserDTO;
import com.nodam.server.dto.MatchDTO;
import com.nodam.server.dto.smsDTO.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class MatchService {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserService userService;

    @Autowired
    SmsService smsService;

    public ArrayList<UserDTO> getUsersFitConditions(UserDTO user, String gender, String purpose, String targetGender, int gradeLimit, int studentNumberLimit){
        ArrayList<UserDTO> findingUsers = userService.getFindingUsers();
        ArrayList<UserDTO> matchedList = new ArrayList<>();
        for (UserDTO otherUser : findingUsers){
            // check purpose
            if (otherUser.getPurpose().equals(purpose))
                continue;

            // check targetGender
            if (targetGender.equals("MALE") && otherUser.getGender().equals("FEMALE"))
                continue;
            else if (targetGender.equals("FEMALE") && otherUser.getGender().equals("MALE"))
                continue;
            if (otherUser.getTargetGender().equals("MALE") && gender.equals("FEMALE"))
                continue;
            else if (otherUser.getTargetGender().equals("FEMALE") && gender.equals("MALE"))
                continue;

            // check gradeLimit and studentNumber
            if (purpose.equals("GET_JUNIOR")){
                if (user.getGrade() <= otherUser.getGrade() || user.getStudentNumber() >= otherUser.getStudentNumber())
                    continue;
            } else if(purpose.equals("GET_SENIOR")) {
                if (user.getGrade() >= otherUser.getGrade() || user.getStudentNumber() <= otherUser.getStudentNumber())
                    continue;
            }
            if (user.getGrade() - gradeLimit > otherUser.getGrade() || user.getGrade() + gradeLimit < otherUser.getGrade())
                continue;
            if (otherUser.getGrade() - otherUser.getGradeLimit() > user.getGrade() || otherUser.getGrade() + otherUser.getGradeLimit() < user.getGrade())
                continue;
            if (user.getStudentNumber() - studentNumberLimit > otherUser.getStudentNumber() || user.getStudentNumber() + studentNumberLimit < otherUser.getStudentNumber())
                continue;
            if (otherUser.getStudentNumber() - otherUser.getStudentNumberLimit() > user.getStudentNumber() || otherUser.getStudentNumber() + otherUser.getStudentNumberLimit() < user.getStudentNumber())
                continue;

            matchedList.add(otherUser);
        }

        return matchedList;
    }

    public ArrayList<UserDTO> getUsersFitConditions(UserDTO user, String gender, String purpose, String targetGender, int gradeLimit, int studentNumberLimit, String targetBoundary){
        ArrayList<UserDTO> findingUsers = userService.getFindingUsers();
        ArrayList<UserDTO> matchedList = new ArrayList<>();
        for (UserDTO otherUser : findingUsers){
            // check purpose
            if (otherUser.getPurpose().equals(purpose))
                continue;

            // check targetGender
            if (targetGender.equals("MALE") && otherUser.getGender().equals("FEMALE"))
                continue;
            else if (targetGender.equals("FEMALE") && otherUser.getGender().equals("MALE"))
                continue;
            if (otherUser.getTargetGender().equals("MALE") && gender.equals("FEMALE"))
                continue;
            else if (otherUser.getTargetGender().equals("FEMALE") && gender.equals("MALE"))
                continue;

            // check gradeLimit and studentNumber
            if (purpose.equals("GET_JUNIOR")){
                if (user.getGrade() <= otherUser.getGrade() || user.getStudentNumber() >= otherUser.getStudentNumber())
                    continue;
            } else if(purpose.equals("GET_SENIOR")) {
                if (user.getGrade() >= otherUser.getGrade() || user.getStudentNumber() <= otherUser.getStudentNumber())
                    continue;
            }
            if (user.getGrade() - gradeLimit > otherUser.getGrade() || user.getGrade() + gradeLimit < otherUser.getGrade())
                continue;
            if (otherUser.getGrade() - otherUser.getGradeLimit() > user.getGrade() || otherUser.getGrade() + otherUser.getGradeLimit() < user.getGrade())
                continue;
            if (user.getStudentNumber() - studentNumberLimit > otherUser.getStudentNumber() || user.getStudentNumber() + studentNumberLimit < otherUser.getStudentNumber())
                continue;
            if (otherUser.getStudentNumber() - otherUser.getStudentNumberLimit() > user.getStudentNumber() || otherUser.getStudentNumber() + otherUser.getStudentNumberLimit() < user.getStudentNumber())
                continue;

            // check targetBoundary
            if (targetBoundary.equals("MAJOR") && !(user.getMajor().equals(otherUser.getMajor())  || user.getMajor().startsWith(otherUser.getMajor().substring(0, otherUser.getMajor().length() - 1)) || otherUser.getMajor().startsWith(user.getMajor().substring(0, user.getMajor().length() - 1))))
                continue;
            else if (targetBoundary.equals("COLLEGE") && !user.getCollege().equals(otherUser.getCollege()))
                continue;
            if (otherUser.getTargetBoundary().equals("MAJOR") && !(user.getMajor().equals(otherUser.getMajor()) || user.getMajor().startsWith(otherUser.getMajor().substring(0, otherUser.getMajor().length() - 1)) || otherUser.getMajor().startsWith(user.getMajor().substring(0, user.getMajor().length() - 1))))
                continue;
            else if (otherUser.getTargetBoundary().equals("COLLEGE") && !user.getCollege().equals(otherUser.getCollege()))
                continue;

            matchedList.add(otherUser);
        }

        return matchedList;
    }


    public Map<String, Integer> getPoolNumbers(String id, String gender, String purpose, String targetGender, int gradeLimit, int studentNumberLimit){
        Map<String, Integer> map = new HashMap<>();

        ArrayList<UserDTO> allUserPool = getUsersFitConditions(userService.getUserById(id),
                gender, purpose, targetGender, gradeLimit, studentNumberLimit);
        ArrayList<UserDTO> collegeUserPool = getUsersFitConditions(userService.getUserById(id),
                gender, purpose, targetGender, gradeLimit, studentNumberLimit, "COLLEGE");
        ArrayList<UserDTO> majorUserPool = getUsersFitConditions(userService.getUserById(id),
                gender, purpose, targetGender, gradeLimit, studentNumberLimit, "MAJOR");

        map.put("major", majorUserPool.size());
        map.put("college", collegeUserPool.size());
        map.put("all", allUserPool.size());

        return map;
    }

    static String messageFormat1 = "안녕하세요 %s 학우님,  'Sejong Peer'입니다. %s 매칭이 완료되었습니다!";
    static String messageFormat2 = "%s (%s/%d학번/%d학년)\n%s";

    public void match(String id, MatchDTO matchDTO) throws Exception{
        UserDTO user = userService.getUserById(id);
        user.setGender(matchDTO.getGender());
        user.setPhoneNumber(matchDTO.getPhoneNumber());
        user.setPurpose(matchDTO.getPurpose());
        user.setTargetGender(matchDTO.getTargetGender());
        user.setGradeLimit(matchDTO.getGradeLimit());
        user.setStudentNumberLimit(matchDTO.getStudentNumberLimit());
        user.setTargetBoundary(matchDTO.getTargetBoundary());
        user.setSearchStart(LocalDateTime.now());
        ArrayList<UserDTO> userPool = getUsersFitConditions(user, user.getGender(), user.getPurpose(), user.getTargetGender(), user.getGradeLimit(), user.getStudentNumberLimit(),  user.getTargetBoundary());
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

            logger.info("매치가 성사됨 ",user.getName() + " " + user.getId() + " " + partner.getName() + " " + partner.getId());

            try {
                String messageToUser1 = String.format(messageFormat1, user.getName(), user.getPurpose().equals("GET_SENIOR") ? "짝선배" : "짝후배");
                String messageToPartner1 = String.format(messageFormat1, partner.getName(), partner.getPurpose().equals("GET_SENIOR") ? "짝선배" : "짝후배");

                String messageToUser2 = String.format(messageFormat2, partner.getName(), partner.getMajor(), partner.getStudentNumber(), partner.getGrade(), partner.getPhoneNumber().substring(0, 3) + '-' + partner.getPhoneNumber().substring(3, 7) + '-' + partner.getPhoneNumber().substring(7));
                String messageToPartner2 = String.format(messageFormat2, user.getName(), user.getMajor(), user.getStudentNumber(), user.getGrade(), user.getPhoneNumber().substring(0, 3) + '-' + user.getPhoneNumber().substring(3, 7) + '-' + user.getPhoneNumber().substring(7));

                smsService.sendSms(new MessageDTO(matchDTO.getPhoneNumber(), messageToUser1));
                smsService.sendSms(new MessageDTO(partner.getPhoneNumber(), messageToPartner1));
                smsService.sendSms(new MessageDTO(matchDTO.getPhoneNumber(), messageToUser2));
                smsService.sendSms(new MessageDTO(partner.getPhoneNumber(), messageToPartner2));
            }
            catch (Exception e){
                throw new Exception("fail to send sms");
            }
        }
    }


    public UserDTO getUser(String id) {
        return userService.getUserById(id);
    }

    static String messageFormat3 = "상대방이 관계를 끊었습니다.";
    public void breakRelationship(String id){
        UserDTO user = userService.getUserById(id);
        if (user.getPartnerId() != null){
            UserDTO partner = userService.getUserById(user.getPartnerId());
            try {
                smsService.sendSms(new MessageDTO(partner.getPhoneNumber(), "상대방이 관계를 끊었습니다."));
            } catch (Exception e) {
                logger.info("break sms fail " + id);
            }
            partner.setFinding(false);
            partner.setPartnerId(null);
            partner.setState("NOT_REGISTER");
            partner.setPurpose(null);
            partner.setTargetGender(null);
            partner.setGradeLimit(0);
            partner.setStudentNumberLimit(0);
            partner.setTargetBoundary(null);
            partner.setSearchStart(null);
            userService.updateUser(partner.getId(), partner);
            logger.info("/break cancle match " + id);
        }
        user.setFinding(false);
        user.setPartnerId(null);
        user.setState("NOT_REGISTER");
        user.setPurpose(null);
        user.setTargetGender(null);
        user.setGradeLimit(0);
        user.setStudentNumberLimit(0);
        user.setTargetBoundary(null);
        user.setSearchStart(null);
        userService.updateUser(id, user);

    }
}
