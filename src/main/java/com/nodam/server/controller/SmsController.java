package com.nodam.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nodam.server.dto.smsDTO.MessageDTO;
import com.nodam.server.dto.smsDTO.SmsResponseDTO;
import com.nodam.server.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

//@RequiredArgsConstructor
@RequestMapping("/sms")
@RestController
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/send")
    public String sendSms(@RequestBody MessageDTO messageDTO) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        SmsResponseDTO response = smsService.sendSms(messageDTO);
        return "200";
    }


}

//2023-02-09 15:25:06.750 ERROR 71227 --- [nio-8080-exec-3] o.a.c.c.C.[.[.[/].[dispatcherServlet]
// : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception
// [Request processing failed; nested exception is org.springframework.web.client.HttpClientErrorException$BadRequest:
// 400 Bad Request: "{"errors":["The content cannot be empty. (root content is mandatory)","The receiver(to) cannot be empty."]
// ,"errorMessage":"Validation failed. 2 error(s)","status":400}"] with root cause