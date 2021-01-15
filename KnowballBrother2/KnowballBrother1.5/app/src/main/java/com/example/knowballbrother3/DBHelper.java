package com.example.knowballbrother3;

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
            +"user_tel text,"
            +"football_score integer,"
            +"football_assist integer,"
            +"football_time integer,"
            +"basketball_score integer,"
            +"basketball_assist integer,"
            +"basketball_time integer,"
            +"volleyball_score integer,"
            +"volleyball_assist integer,"
            +"volleyball_time integer,"
            +"pingpong_wins integer,"
            +"pingpong_round integer,"
            +"pingpong_time integer,"
            +"badminton_wins integer,"
            +"badminton_round integer,"
            +"badminton_time integer)";

    private static final String CREATE_MESSAGE = "create table Message("
            +"message_id integer primary key autoincrement,"
            +"message_picture blob,"
            +"message_title text,"
            +"message_kind text,"
            +"message_content text)";

    private static final String CREATE_EVALUATE = "create table Evaluate("
            +"evaluate_id integer primary key autoincrement,"
            +"evaluate_name text,"
            +"evaluate_title text,"
            +"evaluate_content text)";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,null,version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MESSAGE);
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_EVALUATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Message");
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Evaluate");
        onCreate(db);
    }
}
