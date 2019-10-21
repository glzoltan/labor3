package com.example.labor3;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

//import info.androidhive.sqlite.database.model.HobbyList;
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "hobby_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(HobbyList.CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + HobbyList.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
    public long insertHobby(String hobby, String name) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(HobbyList.COLUMN_HOBBY, hobby);
        values.put(HobbyList.COLUMN_NAME,name);
        // insert row
        long id = db.insert(HobbyList.TABLE_NAME, null, values);


        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public HobbyList getHobby(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(HobbyList.TABLE_NAME,
                new String[]{HobbyList.COLUMN_ID, HobbyList.COLUMN_HOBBY, HobbyList.COLUMN_NAME},
                HobbyList.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        HobbyList hobby = new HobbyList(
                cursor.getInt(cursor.getColumnIndex(HobbyList.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(HobbyList.COLUMN_HOBBY)),
                cursor.getString(cursor.getColumnIndex(HobbyList.COLUMN_NAME)));

        // close the db connection
        cursor.close();

        return hobby;
    }

    public List<HobbyList> getAllNotes() {
        List<HobbyList> hobbies = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + HobbyList.TABLE_NAME + " ORDER BY " +
                HobbyList.COLUMN_NAME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HobbyList note = new HobbyList();
                note.setId(cursor.getInt(cursor.getColumnIndex(HobbyList.COLUMN_ID)));
                note.setHobby(cursor.getString(cursor.getColumnIndex(HobbyList.COLUMN_HOBBY)));
                note.setName(cursor.getString(cursor.getColumnIndex(HobbyList.COLUMN_NAME)));

                hobbies.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return hobbies;
    }
    public List<String> getAllHObbies() {
        List<String> hobbies = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT "+HobbyList.COLUMN_HOBBY+"  FROM " + HobbyList.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                hobbies.add(cursor.getString(cursor.getColumnIndex(HobbyList.COLUMN_HOBBY)));
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return hobbies;
    }
    public int getHobbiesCount() {
        String countQuery = "SELECT  * FROM " + HobbyList.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }
}

