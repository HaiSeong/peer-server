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

    static private SameMajorMap sameMajorMap = new SameMajorMap();

    private boolean isSameMajor(String major1, String major2) {
        if (major1.equals(major2))
            return true;
        else if (sameMajorMap.getCode(major1) >= 0 && sameMajorMap.getCode(major2) >= 0 && sameMajorMap.getCode(major1) == sameMajorMap.getCode(major2))
            return true;

        return false;
    }

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
            if (targetBoundary.equals("MAJOR") && !isSameMajor(user.getMajor(), otherUser.getMajor()))
                continue;
            else if (targetBoundary.equals("COLLEGE") && !user.getCollege().equals(otherUser.getCollege()))
                continue;
            if (otherUser.getTargetBoundary().equals("MAJOR") && !isSameMajor(user.getMajor(), otherUser.getMajor()))
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

        logger.info("getPoolNumbers " + id + " major : " + map.get("major") + " college : " + map.get("college") + " all : " + map.get("all"));

        return map;
    }

    static String messageFormat1 = "안녕하세요 %s 학우님, 'Sejong Peer'입니다. %s 매칭이 완료되었습니다!";
    static String messageFormat2 = "%s님의 kakao id\n%s";

    public void match(String id, MatchDTO matchDTO) throws Exception{
        UserDTO user = userService.getUserById(id);
        user.setGender(matchDTO.getGender());
        user.setPhoneNumber(matchDTO.getPhoneNumber());
        user.setKakaoId(matchDTO.getKakaoId());
        user.setPurpose(matchDTO.getPurpose());
        user.setTargetGender(matchDTO.getTargetGender());
        user.setGradeLimit(matchDTO.getGradeLimit());
        user.setStudentNumberLimit(matchDTO.getStudentNumberLimit());
        user.setTargetBoundary(matchDTO.getTargetBoundary());
        user.setSearchStart(LocalDateTime.now());
        logger.info("match user : " + id + " " + matchDTO);
        ArrayList<UserDTO> userPool = getUsersFitConditions(user, user.getGender(), user.getPurpose(), user.getTargetGender(), user.getGradeLimit(), user.getStudentNumberLimit(),  user.getTargetBoundary());
        if (userPool.isEmpty()) { // 바로 불가능한 경우
            user.setFinding(true);
            user.setState("ON_GOING");
            userService.updateUser(id, user);
            logger.info("match " + id + " goes waiting list");
        }
        else { // 바로 가능한 경우
            UserDTO partner = userPool.get(0);
            user.setFinding(false); partner.setFinding(false);
            user.setState("DONE"); partner.setState("DONE");
            user.setPartnerId(partner.getId()); partner.setPartnerId(user.getId());
            user.setMatchedTime(LocalDateTime.now()); partner.setMatchedTime(LocalDateTime.now());

            userService.updateUser(partner.getId(), partner);
            userService.updateUser(id, user);

            logger.info("match couple matched " + user.getName() + " " + user.getId() + " " + partner.getName() + " " + partner.getId());
            logger.info("match user1 : " + user.toStringShort());
            logger.info("match user2 : " + partner.toStringShort());

            try {
                String messageToUser1 = String.format(messageFormat1, user.getName(), user.getPurpose().equals("GET_SENIOR") ? "짝선배" : "짝후배");
                String messageToPartner1 = String.format(messageFormat1, partner.getName(), partner.getPurpose().equals("GET_SENIOR") ? "짝선배" : "짝후배");

                String messageToUser2 = String.format(messageFormat2, partner.getName(), partner.getKakaoId());
                String messageToPartner2 = String.format(messageFormat2, user.getName(), user.getKakaoId());

                smsService.sendSms(new MessageDTO(matchDTO.getPhoneNumber(), messageToUser1));
                smsService.sendSms(new MessageDTO(partner.getPhoneNumber(), messageToPartner1));
                smsService.sendSms(new MessageDTO(matchDTO.getPhoneNumber(), messageToUser2));
                smsService.sendSms(new MessageDTO(partner.getPhoneNumber(), messageToPartner2));
            }
            catch (Exception e){
                logger.info("match fail to send sms " + user.getId() + " and " + partner.getId());
                throw new Exception("fail to send sms");
            }
        }
    }


    public UserDTO getUser(String id) {
        UserDTO user = userService.getUserById(id);
        if (user.getState().equals("BLOCKED")){
            if (LocalDateTime.now().isAfter(user.getUnblockTime())){
                user.setState("NOT_REGISTER");
                userService.updateUser(id, user);
            }
        }
        return user;
    }

    static String messageFormat3 = "상대방이 관계를 끊었습니다.";
    static String messageFormat4 = "상대방이 관계를 끊었습니다. 상대방은 경고 누적으로 일시정지 되었습니다.";
    public void breakRelationship(String id){
        UserDTO user = userService.getUserById(id);
        logger.info("breakRelationship " + user.getId());
        if (user.getPartnerId() != null){
            UserDTO partner = userService.getUserById(user.getPartnerId());


            if (LocalDateTime.now().isBefore(user.getMatchedTime().plusMinutes(15))) {
                user.setYellowCard(user.getYellowCard() + 3);
                logger.info("breakRelationship " + user.getId() + " got 3 yellow");
            }
            else if (LocalDateTime.now().isBefore(user.getMatchedTime().plusHours(1))) {
                user.setYellowCard(user.getYellowCard() + 2);
                logger.info("breakRelationship " + user.getId() + " got 2 yellow");
            }
            else if (LocalDateTime.now().isBefore(user.getMatchedTime().plusHours(24))) {
                user.setYellowCard(user.getYellowCard() + 1);
                logger.info("breakRelationship " + user.getId() + " got 1 yellow");
            }

            if (user.getYellowCard() > 7){
                user.setState("BLOCKED");
                user.setUnblockTime(LocalDateTime.now().minusMonths(4));
            }
            else if (user.getYellowCard() > 5){
                user.setState("BLOCKED");
                user.setUnblockTime(LocalDateTime.now().plusMonths(1));
            }
            else if (user.getYellowCard() > 3){
                user.setState("BLOCKED");
                user.setUnblockTime(LocalDateTime.now().plusWeeks(1));
            }
            else if (user.getYellowCard() > 1){
                user.setState("BLOCKED");
                user.setUnblockTime(LocalDateTime.now().plusDays(1));
            }
            else {
                user.setState("NOT_REGISTER");
            }

            try {
                if (user.getState().equals("BLOCKED")) {
                    logger.info("breakRelationship block " + user.getId() + " until " + user.getUnblockTime());
                    smsService.sendSms(new MessageDTO(partner.getPhoneNumber(), messageFormat4));
                }
                else
                    smsService.sendSms(new MessageDTO(partner.getPhoneNumber(), messageFormat3));
            } catch (Exception e) {
                logger.info("breakRelationship sms fail " + partner.getId());
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
            partner.setMatchedTime(null);
            partner.setKakaoId(null);
            userService.updateUser(partner.getId(), partner);
        }
        else {
            user.setState("NOT_REGISTER");
        }

        user.setFinding(false);
        user.setPartnerId(null);
        user.setPurpose(null);
        user.setTargetGender(null);
        user.setGradeLimit(0);
        user.setStudentNumberLimit(0);
        user.setTargetBoundary(null);
        user.setSearchStart(null);
        user.setMatchedTime(null);
        user.setKakaoId(null);

        userService.updateUser(id, user);

    }
}
