package com.tech.entite;

import java.sql.Timestamp;

public class Users {

    private int id;
    private String fullName;
    private String email;
    private String password;
    private String contactNo;
    private String gender;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String profile;

    // Default constructor
    public Users() {
    }

    // Parameterized constructor
    public Users(int id, String fullName, String email, String password, String contactNo, String gender, Timestamp createdAt, Timestamp updatedAt, String profile) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.contactNo = contactNo;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.profile = profile;
    }

    // Parameterized constructor without timestamps // get data from user at time of register....through regisertServlet 
    public Users(String fullName, String email, String password, String contactNo, String gender) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.contactNo = contactNo;
        this.gender = gender;
    }

    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
