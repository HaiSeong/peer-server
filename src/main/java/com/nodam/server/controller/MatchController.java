package com.nodam.server.controller;

import com.nodam.server.dto.MatchDTO;
import com.nodam.server.service.AuthService;
import com.nodam.server.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/match")
public class MatchController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private MatchService matchService;

    @Autowired
    private AuthService authService;

    @GetMapping("/pool")
    public  ResponseEntity<?> getPoolNumbers(
            @RequestParam(value = "gender")String gender,
            @RequestParam(value = "purpose")String purpose,
            @RequestParam(value = "targetGender")String targetGender,
            @RequestParam(value = "gradeLimit")int gradeLimit,
            @RequestParam(value = "studentNumberLimit")int studentNumberLimit,
            HttpServletRequest request
    ) {
        try {
            String accessToken = request.getHeader("Authorization");
            String id = authService.validateAccessToken(accessToken);
            return new ResponseEntity<>(matchService.getPoolNumbers(id, gender, purpose, targetGender, gradeLimit, studentNumberLimit), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("bad token", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @PostMapping("/pool")
    public  ResponseEntity<?> match(@RequestBody MatchDTO matchDTO, HttpServletRequest request){
        try {
            String accessToken = request.getHeader("Authorization");
            String id = authService.validateAccessToken(accessToken);
            logger.info("/pool " + id + " " + matchDTO.toString());
            matchService.match(id, matchDTO);
            logger.info("/pool " + id + " success");
            return new ResponseEntity<>("matching", HttpStatus.OK);
        }catch (Exception e){
            logger.info("/pool " + "fail");
            return new ResponseEntity<>("bad token", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(HttpServletRequest request){
        try {
            String accessToken = request.getHeader("Authorization");
            String id = authService.validateAccessToken(accessToken);
            return new ResponseEntity<>(matchService.getUser(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("bad token", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @PostMapping("/break")
    public  ResponseEntity<?> breakRelationship(HttpServletRequest request){
        try {
            String accessToken = request.getHeader("Authorization");
            String id = authService.validateAccessToken(accessToken);
            matchService.breakRelationship(id);
            return new ResponseEntity<>("break", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("bad token", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

}
