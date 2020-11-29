package com.asd.personalinformationsystem;

public class UserHelperClass {

    String userName, userID,  userEmail, userPhone, userOccupation;

    public UserHelperClass() {

    }

    public UserHelperClass(String userName,String userID, String userEmail, String userPhone, String userOccupation) {
        this.userName = userName;
        this.userID = userID;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userOccupation = userOccupation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserOccupation() {
        return userOccupation;
    }

    public void setUserOccupation(String userOccupation) {
        this.userOccupation = userOccupation;
    }


}
