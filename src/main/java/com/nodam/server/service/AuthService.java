package com.nodam.server.service;

import com.nodam.server.dto.LoginDTO;
import com.nodam.server.dto.UserDTO;
import com.nodam.server.security.SecurityService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
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


    public String login(LoginDTO loginDTO, HttpServletResponse response){
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
            return "login failed";

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

        return securityService.createToken(loginDTO.getId(), 30 * 60 * 1000);
    }

    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new ResponseEntity<>("logout", HttpStatus.OK);
    }


    public ResponseEntity<?> refresh(Cookie token) {
        if (token == null)
            return new ResponseEntity<>("no", HttpStatus.UNAUTHORIZED);
        String subject = securityService.getSubject(token.getValue());
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }
}
