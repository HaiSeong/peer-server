package com.nodam.server.dto;

public class MatchDTO {
    private String gender;
    private String phoneNumber;
    private String kakaoId;
    private String purpose;
    private String targetGender;
    private int gradeLimit;
    private int studentNumberLimit;
    private String targetBoundary;

    public MatchDTO(String gender, String phoneNumber, String kakaoId, String purpose, String targetGender, int gradeLimit, int studentNumberLimit, String targetBoundary) {
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.kakaoId = kakaoId;
        this.purpose = purpose;
        this.targetGender = targetGender;
        this.gradeLimit = gradeLimit;
        this.studentNumberLimit = studentNumberLimit;
        this.targetBoundary = targetBoundary;
    }

    public String getKakaoId() {
        return kakaoId;
    }

    public void setKakaoId(String kakaoId) {
        this.kakaoId = kakaoId;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTargetGender() {
        return targetGender;
    }

    public void setTargetGender(String targetGender) {
        this.targetGender = targetGender;
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

    public String getTargetBoundary() {
        return targetBoundary;
    }

    public void setTargetBoundary(String targetBoundary) {
        this.targetBoundary = targetBoundary;
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
                "gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", purpose='" + purpose + '\'' +
                ", targetGender='" + targetGender + '\'' +
                ", gradeLimit=" + gradeLimit +
                ", studentNumberLimit=" + studentNumberLimit +
                ", targetBoundary='" + targetBoundary + '\'' +
                '}';
    }
}
