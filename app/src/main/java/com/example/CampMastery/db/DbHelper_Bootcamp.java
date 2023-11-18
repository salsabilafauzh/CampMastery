//package com.example.CampMastery.db;
//
//import android.annotation.SuppressLint;
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import com.example.CampMastery.Model.Bootcamp;
//
//import org.jetbrains.annotations.Nullable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DbHelper_Bootcamp extends SQLiteOpenHelper {
//    private static final int DATABASE_VERSION = 1;
//    private static String DATABASE_NAME = "CampMastery";
//
//    private static final String TABLE_BOOTCAMPS = "bootcamps";
//
//    private static final String COLUMN_ID = "id";
//    private static final String COLUMN_TITLE = "title";
//    private static final String COLUMN_DESCRIPTION = "description";
//    private static final String COLUMN_START_DATE = "start_date";
//    private static final String COLUMN_END_DATE = "end_date";
//    private static final String COLUMN_COVER = "cover";
//    private static final String  CREATE_TABLE = "CREATE TABLE " + TABLE_BOOTCAMPS + "("
//            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + COLUMN_TITLE + " TEXT,"
//            + COLUMN_DESCRIPTION + " TEXT,"
//            + COLUMN_START_DATE + " TEXT,"
//            + COLUMN_END_DATE + " TEXT,"
//            + COLUMN_COVER + " INTEGER"
//            + ")";
//
//    public DbHelper_Bootcamp(@Nullable Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
////        db.execSQL(CREATE_TABLE);
//        try {
//            db.execSQL(CREATE_TABLE);
//            Log.d("DbHelper_Bootcamp", "Table created successfully");
//        } catch (Exception e) {
//            Log.e("DbHelper_Bootcamp", "Error creating table: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOTCAMPS);
//        onCreate(db);
//    }
//
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
//
//    @SuppressLint("Range")
//    public List<Bootcamp> getAllBootcamps() {
//        List<Bootcamp> bootcampList = new ArrayList<>();
//        String selectQuery = "SELECT * FROM " + TABLE_BOOTCAMPS;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                Bootcamp bootcamp = new Bootcamp();
//                bootcamp.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
//                bootcamp.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
//                bootcamp.setDeskripsi(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
//                bootcamp.setStart(cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE)));
//                bootcamp.setEnd(cursor.getString(cursor.getColumnIndex(COLUMN_END_DATE)));
//                bootcamp.setCover(cursor.getInt(cursor.getColumnIndex(COLUMN_COVER)));
//
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//        return bootcampList;
//    }
//
//
//}
