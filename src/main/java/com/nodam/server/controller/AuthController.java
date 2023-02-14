package com.nodam.server.controller;

import com.nodam.server.dto.LoginDTO;
import com.nodam.server.dto.TokenDTO;
import com.nodam.server.dto.UserDTO;
import com.nodam.server.security.SecurityService;
import com.nodam.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginDTO loginDTO, HttpServletResponse response){
        try {
            String refreshToken = authService.createRefreshToken(loginDTO);
            System.out.println("-------------------2");
            String accessToken = authService.createAccessToken(refreshToken);
            System.out.println("-------------------3");
            response.setHeader("Set-Cookie",
                    "refreshToken=" + refreshToken + ";" +
                            " Path=/; Domain=localhost;" +
                            " HttpOnly; Max-Age=604800;" +
                            " Secure;" +
                            " SameSite=None;");

            Map<String, String> map = new HashMap<>();
            map.put("accessToken", accessToken);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("login failed", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response){
        return authService.logout(response);
    }


    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refreshToken")) {
                    Cookie refreshToken = cookie;
                    String accessToken = authService.createAccessToken(refreshToken.getValue());

                    Map<String, String> map = new HashMap<>();
                    map.put("accessToken", accessToken);
                    return new ResponseEntity<>(map, HttpStatus.OK);
                }
            }
            throw new Exception("no refreshToken");
        } catch (Exception e){
            return new ResponseEntity<>("refresh failed", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

//
//    @GetMapping("/token")
//    public void cookie(HttpServletResponse response) {
//        String jwt = authService.login(new LoginDTO("18010704", "19990808"), response);
//
//        response.setHeader("Set-Cookie", "token=" + jwt + "; Path=/; Domain=localhost; HttpOnly; Max-Age=604800; Secure; SameSite=None;");
//    }
//
//
//    @GetMapping("/check")
//    public void infoCookie(HttpServletRequest request) {
//        Cookie[] cookie = request.getCookies();
//        for (Cookie cok : cookie) {
//            System.out.println("쿠키 이름: " + cok.getName());
//            System.out.println("쿠키 값: " + cok.getValue());
//        }
//    }
}
