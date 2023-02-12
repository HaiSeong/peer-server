package com.nodam.server.controller;

import com.nodam.server.dto.LoginDTO;
import com.nodam.server.dto.TokenDTO;
import com.nodam.server.dto.UserDTO;
import com.nodam.server.security.SecurityService;
import com.nodam.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public String login (@RequestBody LoginDTO loginDTO, HttpServletResponse response, HttpServletRequest request){
        String jwt = authService.login(loginDTO, response);
        response.setHeader("Set-Cookie", "token=" + jwt + "; Path=/; Domain=localhost; HttpOnly; Max-Age=604800; SameSite=None; Secure;");
//        Cookie cookie = new Cookie("token", jwt);
//        cookie.setMaxAge(7 * 24 * 60 * 60);
////        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
//        cookie.setPath("/");
//        cookie.setDomain("localhost");
//        response.addCookie(cookie);

        return "login";
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response){
        return authService.logout(response);
    }


    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue(value = "token", required = false) Cookie token) {

        return authService.refresh(token);
    }


    @GetMapping("/token")
    public void cookie(HttpServletResponse response) {
        String jwt = authService.login(new LoginDTO("18010704", "19990808"), response);

        response.setHeader("Set-Cookie", "token=" + jwt + "; Path=/; Domain=localhost; HttpOnly; Max-Age=604800; Secure; SameSite=None;");
    }


    @GetMapping("/check")
    public void infoCookie(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();
        for (Cookie cok : cookie) {
            System.out.println("쿠키 이름: " + cok.getName());
            System.out.println("쿠키 값: " + cok.getValue());
        }
    }
}
