package org.pkb.springlogin.model;

import java.io.Serializable;

/**
 * Created by Rakshith on 7/1/17.
 */

public class UserInfo implements Serializable {

    private int id;
    private String userName;
    private String password;
    private String email;
    private String reCaptcha;
    private String userRole;
    private String dob;
    private int enabled;

    public UserInfo() {

    }

    public UserInfo(String userName, String password, int enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReCaptcha() {
        return reCaptcha;
    }

    public void setReCaptcha(String reCaptcha) {
        this.reCaptcha = reCaptcha;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", reCaptcha='" + reCaptcha + '\'' +
                ", userRole='" + userRole + '\'' +
                ", dob='" + dob + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}