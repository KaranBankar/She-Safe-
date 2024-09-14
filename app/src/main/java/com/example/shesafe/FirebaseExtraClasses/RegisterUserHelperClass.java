package com.example.shesafe.FirebaseExtraClasses;

public class RegisterUserHelperClass {
    String userName;
    String UserMobileNo;
    String userPassword;
    String userMail;

    public RegisterUserHelperClass(String userName) {
        this.userName = userName;
    }

    public RegisterUserHelperClass(String userName, String userMobileNo, String userPassword, String userMail) {
        this.userName = userName;
        UserMobileNo = userMobileNo;
        this.userPassword = userPassword;
        this.userMail = userMail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobileNo() {
        return UserMobileNo;
    }

    public void setUserMobileNo(String userMobileNo) {
        UserMobileNo = userMobileNo;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }
}
