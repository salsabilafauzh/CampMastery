package com.example.CampMastery.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.CampMastery.Model.Bookmark;
import com.example.CampMastery.Model.Bootcamp;
import com.example.CampMastery.Model.User;
import com.example.CampMastery.Session.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class DbHelper_User extends SQLiteOpenHelper {

    private SessionManager sessionManager;

    public static String DATABASE_NAME = "CampMastery";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";

    private static final String TABLE_BOOTCAMPS = "bootcamps";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_START_DATE = "start_date";
    private static final String COLUMN_END_DATE = "end_date";
    private static final String COLUMN_COVER = "cover";

    private static final String TABLE_BOOKMARKS = "bookmarks";
    private static final String COLUMN_ID_USER = "id_user";
    private static final String COLUMN_ID_BOOTCAMP_BOOKMARK = "id_bootcamp";
    private static final String COLUMN_ID_BOOKMARK = "id_bookmark";



    private static final String  CREATE_TABLE_BOOTCAMPS = "CREATE TABLE " + TABLE_BOOTCAMPS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TITLE + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_START_DATE + " TEXT,"
            + COLUMN_END_DATE + " TEXT,"
            + COLUMN_COVER + " INTEGER"
            + ")";

    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USER + "(" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    KEY_USERNAME + " TEXT, " +
                    KEY_EMAIL + " TEXT, " +
                    KEY_PASSWORD + " TEXT"+
                    ")";

    private static final String CREATE_TABLE_BOOKMARKS = "CREATE TABLE " + TABLE_BOOKMARKS + "("
            + COLUMN_ID_USER + " INTEGER,"
            + COLUMN_ID_BOOTCAMP_BOOKMARK + " INTEGER,"
            + COLUMN_ID_BOOKMARK + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "FOREIGN KEY (" + COLUMN_ID_USER + ") REFERENCES " + TABLE_USER + "(" + KEY_ID + "),"
            + "FOREIGN KEY (" + COLUMN_ID_BOOTCAMP_BOOKMARK + ") REFERENCES " + TABLE_BOOTCAMPS + "(" + COLUMN_ID + ")"
            + ")";

    public DbHelper_User(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.sessionManager = new SessionManager(context);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_BOOTCAMPS);
        db.execSQL(CREATE_TABLE_BOOKMARKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_BOOTCAMPS + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_BOOKMARKS+"'");
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
    public int getUserId(String email, String password) {
        String selectQuery = "SELECT " + KEY_ID + " FROM " + TABLE_USER +
                " WHERE email = '" + email + "' AND password = '" + password + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int userId = -1; // Default value if no user is found

        try {
            if (cursor.moveToFirst()) {
                userId = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                // Add other fields as needed
            }
        } finally {
            cursor.close(); // Make sure to close the cursor when you're done with it
        }

        return userId;
    }

    @SuppressLint("Range")
    public User getUserByEmail(String email) {
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

    public long addNewUser(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        long insert = db.insert(TABLE_USER, null, values);
        return insert;
    }

    public int updateUserDetail(int id, String username, String email, String alamat, int usia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_EMAIL, email);
        return db.update(TABLE_USER, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

//    public void addBootcamp(String title, String desc, String start, String end, int cover) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_TITLE, title);
//        values.put(COLUMN_DESCRIPTION, desc);
//        values.put(COLUMN_START_DATE, start);
//        values.put(COLUMN_END_DATE, end);
//        values.put(COLUMN_COVER, cover);
//        db.insert(TABLE_BOOTCAMPS, null, values);
//        db.close();
//    }

    @SuppressLint("Range")
    public Bootcamp getBootcamp(int idBootcamp) {
        SQLiteDatabase db = this.getReadableDatabase();
//        Bootcamp bootcamp = null;
        Bootcamp objBootcamp = new Bootcamp();
        String selectQuery = "SELECT * FROM " + TABLE_BOOTCAMPS + " WHERE " + COLUMN_ID + " = " + idBootcamp;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
             int bootcampId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String bootcampTitle = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
            String descBootcamp = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
            String startDate = cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE));
            String endDate = cursor.getString(cursor.getColumnIndex(COLUMN_END_DATE));
//            String coverImage = cursor.getString(cursor.getColumnIndex(COLUMN_COVER));


            objBootcamp.setTitle(bootcampTitle);
            objBootcamp.setDeskripsi(descBootcamp);
            objBootcamp.setStart(startDate);
            objBootcamp.setEnd(endDate);
//            objBootcamp.setCover();
        }

        cursor.close();
        return objBootcamp;
    }



    public void addBootcamps(List<Bootcamp> bootcamps) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (Bootcamp bootcamp : bootcamps) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, bootcamp.getTitle());
            values.put(COLUMN_DESCRIPTION, bootcamp.getDeskripsi());
            values.put(COLUMN_START_DATE, bootcamp.getStart());
            values.put(COLUMN_END_DATE, bootcamp.getEnd());
//        values.put(COLUMN_COVER, bootcamp.getCover());
            db.insert(TABLE_BOOTCAMPS, null, values);
        }

        db.close();
    }

    @SuppressLint("Range")
    public List<Bootcamp> getAllBootcamps() {
        List<Bootcamp> bootcampList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_BOOTCAMPS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Bootcamp bootcamp = new Bootcamp();
                bootcamp.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                bootcamp.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                bootcamp.setDeskripsi(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                bootcamp.setStart(cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE)));
                bootcamp.setEnd(cursor.getString(cursor.getColumnIndex(COLUMN_END_DATE)));
                bootcamp.setCover(cursor.getInt(cursor.getColumnIndex(COLUMN_COVER)));
                bootcampList.add(bootcamp);
            } while (cursor.moveToNext());

        }

//        cursor.close();
//        db.close();
        return bootcampList;
    }

    public void addBookmark( int bootcampId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_USER, sessionManager.getUserId());
        values.put(COLUMN_ID_BOOTCAMP_BOOKMARK, bootcampId);
        // Inserting Row
        db.insert(TABLE_BOOKMARKS, null, values);
        db.close(); // Closing database connection
    }

    public void removeBookmark(int bootcampId) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Specify the WHERE clause
        String whereClause = COLUMN_ID_BOOTCAMP_BOOKMARK + " = ? ";

        // Specify the values for the WHERE clause
        String[] whereArgs = {String.valueOf(bootcampId)};

        // Delete the row from the bookmarks table
        db.delete(TABLE_BOOKMARKS, whereClause, whereArgs);

        // Close the database connection
        db.close();
    }

    @SuppressLint("Range")
    public List<Bootcamp> getBookmarkedBootcamps() {

        List<Bootcamp> bookmarkedBootcamps = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_BOOTCAMPS +
                " INNER JOIN " + TABLE_BOOKMARKS +
                " ON " + TABLE_BOOTCAMPS + "." + COLUMN_ID + " = " +
                TABLE_BOOKMARKS + "." + COLUMN_ID_BOOTCAMP_BOOKMARK +
                " WHERE " + TABLE_BOOKMARKS + "." + COLUMN_ID_USER + " = " + sessionManager.getUserId();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all rows and add bookmarked bootcamps to the list
        if (cursor.moveToFirst()) {
            do {
                Bootcamp bootcamp = new Bootcamp();
                bootcamp.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                bootcamp.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                bootcamp.setDeskripsi(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                bootcamp.setStart(cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE)));
                bootcamp.setEnd(cursor.getString(cursor.getColumnIndex(COLUMN_END_DATE)));
                bootcamp.setCover(cursor.getInt(cursor.getColumnIndex(COLUMN_COVER)));

                bookmarkedBootcamps.add(bootcamp);
            } while (cursor.moveToNext());
        }
//
//        cursor.close();
//        db.close();

        return bookmarkedBootcamps;
    }



    public boolean isBootcampBookmarked(int bootcampId) {
        SQLiteDatabase db = this.getReadableDatabase();

        int userId = sessionManager.getUserId();

        // Specify the table and columns you want to query
        String table = TABLE_BOOKMARKS;
        String[] columns = {COLUMN_ID_BOOKMARK};

        // Specify the WHERE clause
        String selection = COLUMN_ID_USER + " = ? AND " + COLUMN_ID_BOOTCAMP_BOOKMARK + " = ?";
        String[] selectionArgs = {String.valueOf(userId), String.valueOf(bootcampId)};
        // Query the database
        Cursor cursor = db.query(table, columns, selection, selectionArgs, null, null, null);

        // Check if the cursor has any rows (bootcamp is bookmarked)
        boolean isBookmarked = cursor.getCount() > 0;

//        // Close the cursor and database
//        cursor.close();
//        db.close();

        return isBookmarked;
    }

    public void clearSession(){

        sessionManager.clearSession();

    }
}
