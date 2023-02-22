package com.nodam.server.controller;

import com.nodam.server.dto.LoginDTO;
import com.nodam.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${client.domain}")
    private String clientDomain;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        try {
            String refreshToken = authService.createRefreshToken(loginDTO);
            String accessToken = authService.createAccessToken(refreshToken);
            response.setHeader("Set-Cookie",
                    "refreshToken=" + refreshToken + ";" +
                            " Path=/; " +
                            "Domain=" + clientDomain + ";" +
                            " HttpOnly; Max-Age=604800;" +
                            " Secure;" +
                            " SameSite=None;");

            Map<String, String> map = new HashMap<>();
            map.put("accessToken", accessToken);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("login failed", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        response.setHeader("Set-Cookie",
                "refreshToken=" + ";" +
                        " Path=/; " +
                        "Domain=" + clientDomain + ";" +
                        " HttpOnly; Max-Age=0;" +
                        " Secure;" +
                        " SameSite=None;");
        return new ResponseEntity<>("logout", HttpStatus.OK);
    }


    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request) {
        return authService.refresh(request);
    }
}