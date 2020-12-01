package com.asd.personalinformationsystem;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey
    private String Phone;

    private String Name;

    private String Email;

    private String Occupation;

    public Note(String phone, String name, String email, String occupation) {
        Phone = phone;
        Name = name;
        Email = email;
        Occupation = occupation;
    }

    public String getPhone() {
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
}
