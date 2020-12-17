package com.asd.personalinformationsystem;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class User {
    @NonNull
    @PrimaryKey
    private String Phone;

    private String Name;

    private String Email;

    private String Occupation;

    public User() {

    }

    public User(String phone, String name, String email, String occupation) {
        Phone = phone;
        Name = name;
        Email = email;
        Occupation = occupation;
    }

 /*   public String getPhone() {
        return Phone;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getOccupation() {
        return Occupation;
    }
    */

    @NonNull
    public String getPhone() {
        return Phone;
    }

    public void setPhone(@NonNull String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }
}
