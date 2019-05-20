package jakyungyoon.jacalendar.calendar.jaycalendar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTableLockedException;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    TabDBhelper tabDBhelper;
    SQLiteDatabase db;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
       // appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
        viewPager = (ViewPager)findViewById(R.id.viewPager_Id);

        tabDBhelper = new TabDBhelper(this);
        db = tabDBhelper.getWritableDatabase();

        //adding fragment
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());



        adapter.AddFragment(new FragmentMonthly(),"Monthly");
        adapter.AddFragment(new FragmentWeekly(),"Weekly");

        adapter.AddFragment(new FragmentDay(),"Day");
        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        cursor = db.rawQuery("select tabNum from tab_table",null);
        if(cursor.moveToLast()){
            TabLayout.Tab tab = tabLayout.getTabAt(cursor.getInt(0));
            tab.select();
        }

        int tabtab =tabLayout.getSelectedTabPosition();

        Log.d("tabtab",tabtab+"");



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                db.execSQL("INSERT INTO tab_table"+" VALUES(null, " + position +
                        ");");
                Log.d("tabtab",position+"");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }
}
