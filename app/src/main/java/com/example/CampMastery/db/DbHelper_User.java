package com.example.CampMastery.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.CampMastery.Model.User;

import java.util.ArrayList;

public class DbHelper_User extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "CampMastery";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";

    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USER + "(" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    KEY_USERNAME + " TEXT, " +
                    KEY_EMAIL + " TEXT, " +
                    KEY_PASSWORD + " TEXT"+
                    ")";



    public DbHelper_User(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        onCreate(db);
    }



    @SuppressLint("Range")
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userModelArrayList = new ArrayList<User>();
        String selectQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                User userData = new User();
                userData.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                userData.setUsername(c.getString(c.getColumnIndex(KEY_USERNAME)));
                userData.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                // adding to Students list
                userModelArrayList.add(userData);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

    public Cursor getUser(String email, String password) {
        String selectQuery = "SELECT email, password FROM " + TABLE_USER + " WHERE email = '" + email + "' AND password = '" + password + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }

    @SuppressLint("Range")
    public User getUserByEmail(String email) {
        // Note: This example directly concatenates the email into the query string,
        // but it's not recommended due to the risk of SQL injection.
        // Ensure that the email is properly sanitized and validated.
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_EMAIL + " = '" + email + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        User user = null;

        try {
            if (cursor.moveToFirst()) {
                user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                // Add other fields as needed
            }
        } finally {
            cursor.close(); // Make sure to close the cursor when you're done with it
        }

        return user;
    }





    public long addNewUser(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME,username);
        values.put(KEY_EMAIL,email);
        values.put(KEY_PASSWORD,password);
        long insert = db.insert(TABLE_USER,null,values);
        return insert;
    }
    public int updateUserDetail(int id,  String username, String email, String alamat, int usia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_EMAIL,email);
        return db.update(TABLE_USER, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
