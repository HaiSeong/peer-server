package com.nodam.server.dto;

public class UserDTO {
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String gender;
    private String major;
    private String college;
    private int studentNumber;
    private int grade;
    private boolean finding = false;
    private String role;
    private boolean sameGender;
    private String range;

    public UserDTO() {
        this.id = "";
        this.password = "";
        this.name = "";
        this.major = "";
        this.studentNumber = 0;
        this.grade = 1;
        this.finding = false;
    }

    public UserDTO(String id, String password, String name, String major, int studentNumber, int grade, boolean finding) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.major = major;
        this.studentNumber = studentNumber;
        this.grade = grade;
        this.finding = finding;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isSameGender() {
        return sameGender;
    }

    public void setSameGender(boolean sameGender) {
        this.sameGender = sameGender;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ", major='" + major + '\'' +
                ", studentNumber=" + studentNumber +
                ", grade=" + grade +
                ", finding=" + finding +
                ", role='" + role + '\'' +
                ", sameGender=" + sameGender +
                ", range='" + range + '\'' +
                '}';
    }
}
