package com.demoapp.demoappbox.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;

public class UserDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "users.db";

    public UserDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT," + "roll TEXT," + "subject TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
