package com.sudo248.invoiceservice.Controller.dto;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable {
    private String userId;
    private String  fullName, phone, bio, gender, avatar, cover, address;
    private Date dob;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public UserDto(String userId) {
        this.userId = userId;
    }

    public UserDto() {
    }

    public UserDto(String userId, String fullName, String phone, String bio, String gender, String avatar, String cover, String address, Date dob) {
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
        this.bio = bio;
        this.gender = gender;
        this.avatar = avatar;
        this.cover = cover;
        this.address = address;
        this.dob = dob;
    }
}