package jakyungyoon.jacalendar.calendar.jaycalendar;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.Month;

public class CalendarDBhelper extends SQLiteOpenHelper {
    private final static String DB_NAME ="calendar_table";
    public  final static String TABLE_NAME = "calendar_table";
    public  final static String YEAR= "year";
    public  final static String MONTH="month";
    public  final static String DAY = "day";
    public  final static String DAYOFWEEK = "dayOfWeek";
    public  final static String CONTENT = "content";


    public CalendarDBhelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME+" ( _id integer primary key, "+YEAR+" INTEGER,"+ MONTH+" INTEGER,"+DAY
                +" INTEGER," + DAYOFWEEK+" INTEGER,"+CONTENT +" TEXT);");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES(1, 2019, 5, 15, 4,'heloolodldodldodeold');");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES(2, 2019, 5, 15, 4,'why do i');");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES(3, 2019, 5, 16, 4,'why do i');");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES(4, 2019, 5, 17, 4,'why do i');");




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
