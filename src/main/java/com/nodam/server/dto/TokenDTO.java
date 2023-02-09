package com.nodam.server.dto;

public class TokenDTO {
    private String token;

    public TokenDTO() {
        token = "";
    }

    public TokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
