package com.nodam.server.dto.smsDTO;

public class MessageDTO {
    String to;
    String content;

    public MessageDTO() {
        this.to = null;
        this.content = null;
    }

    public MessageDTO(String to, String content) {
        this.to = to;
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "to='" + to + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}