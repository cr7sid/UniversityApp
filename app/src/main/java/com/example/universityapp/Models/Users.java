package com.example.universityapp.Models;

public class Users {

    private String profilePic, email, userId, password, phone;

    public Users() {

    }

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Users(String phone) {
        this.phone = phone;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Users(String profilePic, String email, String userId, String password, String phone) {
        this.profilePic = profilePic;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.phone = phone;
    }
}



