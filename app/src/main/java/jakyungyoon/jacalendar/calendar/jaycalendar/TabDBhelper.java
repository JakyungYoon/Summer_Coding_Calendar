package jakyungyoon.jacalendar.calendar.jaycalendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TabDBhelper extends SQLiteOpenHelper {
    private final static String DB_NAME ="tb_table";
    private final static String TABLE_NAME = "tab_table";
    private final static String TABNUM = "tabNum";

    public TabDBhelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME+" ( _id integer primary key, "+TABNUM+" INTEGER);");
        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES(1, 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
