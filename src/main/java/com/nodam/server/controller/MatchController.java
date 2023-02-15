package com.nodam.server.controller;

import com.nodam.server.dto.matchDTO.MatchDTO2;
import com.nodam.server.dto.matchDTO.MatchDTO;
import com.nodam.server.service.AuthService;
import com.nodam.server.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @Autowired
    private AuthService authService;

    @GetMapping("/pool")
    public  ResponseEntity<?> getPoolNumbers(@RequestBody MatchDTO matchDTO, HttpServletRequest request){
        try {
            String accessToken = request.getHeader("Authorization");
            String id = authService.validateAccessToken(accessToken);
            return new ResponseEntity<>(matchService.getPoolNumbers(id, matchDTO.getPurpose(), matchDTO.getTargetGender()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("bad token", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @PostMapping("/pool")
    public  ResponseEntity<?> appendPool(@RequestBody MatchDTO2 matchDTO, HttpServletRequest request){
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getStatus(HttpServletRequest request){
        try {
            String accessToken = request.getHeader("Authorization");
            String id = authService.validateAccessToken(accessToken);
            return new ResponseEntity<>(matchService.getStatus(id), HttpStatus.OK);
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
