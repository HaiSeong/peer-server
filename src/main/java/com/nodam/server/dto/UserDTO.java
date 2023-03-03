package com.nodam.server.dto;

import java.time.LocalDateTime;

public class UserDTO {
    private String id;
    private String name;
    private String major;
    private String college;
    private int studentNumber;
    private int grade;
    private boolean finding;
    private String state;
    private String gender;
    private String phoneNumber;
    private String kakaoId;
    private String purpose;
    private String targetGender;
    private String targetBoundary;
    private int gradeLimit;
    private int studentNumberLimit;
    private LocalDateTime searchStart;
    private String partnerId;
    private int yellowCard;
    private LocalDateTime matchedTime;
    private LocalDateTime unblockTime;

    public UserDTO() {
        this.id = "";
        this.name = "";
        this.major = "";
        this.studentNumber = 0;
        this.grade = 1;
        this.finding = false;
        this.state = "NOT_REGISTER";
        this.yellowCard = 0;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getMajor() {
        return major;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
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

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTargetGender() {
        return targetGender;
    }

    public void setTargetGender(String targetGender) {
        this.targetGender = targetGender;
    }

    public String getTargetBoundary() {
        return targetBoundary;
    }

    public void setTargetBoundary(String targetBoundary) {
        this.targetBoundary = targetBoundary;
    }

    public int getGradeLimit() {
        return gradeLimit;
    }

    public void setGradeLimit(int gradeLimit) {
        this.gradeLimit = gradeLimit;
    }

    public int getStudentNumberLimit() {
        return studentNumberLimit;
    }

    public void setStudentNumberLimit(int studentNumberLimit) {
        this.studentNumberLimit = studentNumberLimit;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public LocalDateTime getSearchStart() {
        return searchStart;
    }

    public void setSearchStart(LocalDateTime searchStart) {
        this.searchStart = searchStart;
    }

    public LocalDateTime getMatchedTime() {
        return matchedTime;
    }

    public void setMatchedTime(LocalDateTime matchedTime) {
        this.matchedTime = matchedTime;
    }

    public String getKakaoId() {
        return kakaoId;
    }

    public void setKakaoId(String kakaoId) {
        this.kakaoId = kakaoId;
    }

    public int getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(int yellowCard) {
        this.yellowCard = yellowCard;
    }

    public LocalDateTime getUnblockTime() {
        return unblockTime;
    }

    public void setUnblockTime(LocalDateTime unblockTime) {
        this.unblockTime = unblockTime;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", college='" + college + '\'' +
                ", studentNumber=" + studentNumber +
                ", grade=" + grade +
                ", finding=" + finding +
                ", status='" + state + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", purpose='" + purpose + '\'' +
                ", targetGender='" + targetGender + '\'' +
                ", targetBoundary='" + targetBoundary + '\'' +
                ", searchStart=" + searchStart +
                ", partnerId='" + partnerId + '\'' +
                '}';
    }
}
