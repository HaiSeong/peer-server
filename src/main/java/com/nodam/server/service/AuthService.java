package com.nodam.server.service;

import com.nodam.server.dto.LoginDTO;
import com.nodam.server.dto.UserDTO;
import com.nodam.server.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Service
public class AuthService {
    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;


    public ResponseEntity<?> login(LoginDTO loginDTO, HttpServletResponse response){
        WebClient.Builder builder = WebClient.builder();
        Map sejongResponse = builder.build()
                .post()
                .uri("https://auth.imsejong.com/auth?method=ClassicSession")
                .body(Mono.just(new LoginDTO(loginDTO.getId(), loginDTO.getPw())), LoginDTO.class)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map result = (Map) sejongResponse.get("result");

        boolean isAuth = (boolean) result.get("is_auth");
        if (!isAuth)
            return new ResponseEntity("login failed", HttpStatus.OK);

        if (!userService.isUser(loginDTO.getId())) {
            UserDTO userDTO = new UserDTO();
            System.out.println(userDTO.toString());
            userDTO.setId(loginDTO.getId());
            userDTO.setPassword(loginDTO.getPw());
            Map body = (Map) result.get("body");
            userDTO.setName((String) body.get("name"));
            userDTO.setMajor((String) body.get("major"));
            userDTO.setGrade(Integer.valueOf((String) body.get("grade")));
            System.out.println(userDTO.toString());
            userService.insertUser(userDTO);
        }

        String jwt = securityService.createToken(loginDTO.getId(), 30 * 60 * 1000);
        Cookie cookie = new Cookie("token", jwt);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new ResponseEntity<>("logout", HttpStatus.OK);
    }
}
