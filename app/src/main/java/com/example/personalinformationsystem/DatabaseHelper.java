package com.example.personalinformationsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String database_name ="persons.db";
    public static final String table_name ="per.db";

    public static final String person_id ="person_id";
    public static final String person_name ="person_name";
    public static final String person_birth ="person_birth";
    public static final String person_address ="person_address";
    public static final String person_email ="person_email";
    public static final String person_mobile ="person_mobile";
    public static final String person_qualification ="person_qualification";
    public static final String person_school ="person_school";


    public DatabaseHelper(@Nullable Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + table_name +"(person_id INTEGER PRIMARY KEY AUTOINCREMENT ,person_name text , person_birth text , person_address text,person_email text , person_mobile text, person_qualification text, person_school text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {

        db.execSQL("Drop table if exists " + table_name);
        onCreate(db);


    }

    public boolean insertPerson(String person_name_s, String person_birth_s, String person_address_s,String person_email_s,String person_mobile_s,String person_qualification_s,String person_school_s){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(person_name ,person_name_s);
        contentValues.put(person_birth,person_birth_s);
        contentValues.put(person_address,person_address_s);
        contentValues.put(person_email,person_email_s);
        contentValues.put(person_mobile,person_mobile_s);
        contentValues.put(person_qualification,person_qualification_s);
        contentValues.put(person_school,person_school_s);

        Long success =sqLiteDatabase.insert(table_name ,null, contentValues);

        if (success == -1){

            return false;
        }else{

            return true;
        }
    }


}
