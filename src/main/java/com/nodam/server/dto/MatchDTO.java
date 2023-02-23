package com.nodam.server.dto;

public class MatchDTO {
    private String gender;
    private String phoneNumber;
    private String purpose;
    private String targetGender;
    private int gradeLimit;
    private int studentNumberLimit;
    private String targetBoundary;

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
        return "AppendPoolDTO{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", purpose='" + purpose + '\'' +
                ", targetGender='" + targetGender + '\'' +
                ", targetBoundary='" + targetBoundary + '\'' +
                '}';
    }
}
