package com.nodam.server.service;

import com.nodam.server.dto.LoginDTO;
import com.nodam.server.dto.UserDTO;
import com.nodam.server.repository.UserRepository;
import com.nodam.server.security.SecurityService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class MatchService {
    @Autowired
    UserRepository userRepository;

    public ArrayList<UserDTO> getUsersFitConditions(String gender, String major, String college, String role, boolean sameGender, String range){
        ArrayList<UserDTO> findingUsers = userRepository.getFindingUsers();
        ArrayList<UserDTO> matchedList = new ArrayList<>();
        for (UserDTO user : findingUsers){
            if (user.getRole().equals(role))
                continue;

            if (sameGender && !gender.equals(user.getGender()))
                continue;
            else if (user.isSameGender() && !user.getGender().equals(gender))
                continue;

            if (range.equals("sameMajor") && !user.getMajor().equals(major))
                continue;
            else if (range.equals("sameCollege") && !user.getCollege().equals(college))
                continue;
            if (user.getRange().equals("sameMajor") && !major.equals(user.getMajor()))
                continue;
            else if (user.getRange().equals("sameCollege") && !college.equals(user.getCollege()))
                continue;

            matchedList.add(user);
        }

        return matchedList;
    }

}
