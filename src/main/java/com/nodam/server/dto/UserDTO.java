package com.nodam.server.dto;

public class UserDTO {
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private boolean gender;
    private String major;
    private boolean finding = false;

    public UserDTO() {
        this.id = "";
        this.password = "";
        this.name = "";
        this.phoneNumber = "";
        this.gender = false;
        this.major = "";
        this.finding = false;
    }
    public UserDTO(String id, String password, String name, String phoneNumber, boolean gender, String major, boolean finding) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.major = major;
        this.finding = finding;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public boolean isFinding() {
        return finding;
    }

    public void setFinding(boolean finding) {
        this.finding = finding;
    }
}
