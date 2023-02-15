package com.nodam.server.dto;

public class UserDTO {
    private String id;
    private String password;
    private String name;
    private String major;
    private String college;
    private int studentNumber;
    private int grade;
    private boolean finding = false;
    private String status;
    private String phoneNumber;
    private String purpose;
    private String targetGender;
    private String targetBoundary;
    private String partnerId;

    public UserDTO() {
        this.id = "";
        this.password = "";
        this.name = "";
        this.major = "";
        this.studentNumber = 0;
        this.grade = 1;
        this.finding = false;
        this.status = "NOT_REGISTER";
    }

    public UserDTO(String id, String password, String name, String major, String college, int studentNumber, int grade, boolean finding, String status) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.major = major;;
        this.college = college;
        this.studentNumber = studentNumber;
        this.grade = grade;
        this.finding = finding;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", major='" + major + '\'' +
                ", college='" + college + '\'' +
                ", studentNumber=" + studentNumber +
                ", grade=" + grade +
                ", finding=" + finding +
                ", status='" + status + '\'' +
                ", purpose='" + purpose + '\'' +
                ", targetGender='" + targetGender + '\'' +
                ", targetBoundary='" + targetBoundary + '\'' +
                '}';
    }
}
