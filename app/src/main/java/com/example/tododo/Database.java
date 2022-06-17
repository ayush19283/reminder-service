package com.example.tododo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "tasks";
    private static final String TASK_ID = "id";
    private static final String TASK_TITLE = "title";
   // private static final String TASK_DESCP = "description";
    private static final String TASK_STATUS = "status";
  //  private static final String TASK_DATE = "date_created";
  //  private static final String TASK_REMIND_DATE = "reminder_date";



    // creating a constructor for our database handler.
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE tasks (task_id integer PRIMARY KEY autoincrement, title string, status string)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
        db.execSQL("CREATE TABLE auth (token string)");
        Log.d("hhhhhhhhhhhhheeeeeere", "Inserted");
        Context context;

        db.execSQL("INSERT INTO tasks (title, status)values('Hello','n')");
    }
    public void add_task(String title) {

        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("dataaaaaaaaaaaaaaa", "Inside current data");
        String query = String.format("INSERT INTO tasks (title,  status) values ('%1$s', 'n')", title);
        db.execSQL(query);
    }

    public ArrayList<ArrayList<Object>> get_all_task_details(String status_type) {
        ArrayList<ArrayList<Object>> return_array = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Log.d("dataaaaaaaaaaaaaaa", "Inside current data");

        Cursor cursor = db.rawQuery("SELECT * FROM tasks where status='"+status_type+"'"+" ORDER by task_id desc", null);

        if (cursor.moveToFirst()) {
            int counter = 0;
            do {
                ArrayList<Object> array_member = new ArrayList<>();
                int tsk_id = cursor.getInt(0);
                String title = cursor.getString(1);
                String status = cursor.getString(2);
//                String status = cursor.getString(3);
//                String dc = cursor.getString(4);
//                String rd = cursor.getString(5);

                array_member.add(tsk_id);
                array_member.add(title);
               // array_member.add(desp);
                array_member.add(status);
        //        array_member.add(dc);
         //       array_member.add(rd);

                Log.d("todotodo", String.valueOf(tsk_id));

                return_array.add(array_member);


            }
            while (cursor.moveToNext()) ;
        }
        Log.d("mytodo", "hereeeeeee");

        return return_array;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.d("a", "hereeeeeeeeeeeeee");
    }
}
