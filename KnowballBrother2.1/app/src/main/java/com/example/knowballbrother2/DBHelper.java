package com.example.knowballbrother2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private Context mcontext;

    private static final String CREATE_USER = "create table User("
            +"user_id integer primary key autoincrement,"
            +"user_account text,"
            +"user_password text,"
            +"user_name text,"
            +"user_sex text,"
            +"user_college text,"
            +"user_class text,"
            +"user_tel text)";

    private static final String CREATE_MESSAGE = "create table Message("
            +"message_id integer primary key autoincrement,"
            +"message_picture blob,"
            +"message_title text,"
            +"message_kind text,"
            +"message_content text)";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,null,version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MESSAGE);
        db.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Message");
        db.execSQL("drop table if exists User");
        onCreate(db);
    }
}
