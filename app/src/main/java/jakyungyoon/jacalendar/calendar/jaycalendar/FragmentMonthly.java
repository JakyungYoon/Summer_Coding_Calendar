package jakyungyoon.jacalendar.calendar.jaycalendar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class FragmentMonthly extends Fragment{

    View view;
    private CalendarView mCalendarView;
    CalendarDBhelper mCalendarDBhelper;
    SQLiteDatabase db;
    Cursor cursor;
    Typeface font;
    List<WeeklyContentItem> mContentData;
    MonthlyContentAdapter monthlyContentAdapter;


    static
    {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.monthly_fragment,container,false);
        mCalendarView = (CalendarView)view.findViewById(R.id.monthlyCalendar);
        mCalendarDBhelper = new CalendarDBhelper(getActivity());
        db = mCalendarDBhelper.getWritableDatabase();


        Date date= Calendar.getInstance().getTime();
        try {
            mCalendarView.setDate(date);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }

        cursor = db.rawQuery("select * from calendar_table", null);

        List<EventDay> mEventList = new ArrayList<>();

        if(cursor.moveToFirst()){
            do {
                Calendar cal = Calendar.getInstance();

                cal.set(cursor.getInt(1), cursor.getInt(2)-1,cursor.getInt(3));

                mEventList.add(new EventDay(cal,R.drawable.ic_fiber_manual_record_red_24dp));
                mCalendarView.setEvents(mEventList);
            }while (cursor.moveToNext() != false);
        }
        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                int year = clickedDayCalendar.get(Calendar.YEAR);
                int month = clickedDayCalendar.get(Calendar.MONTH);
                int day = clickedDayCalendar.get(Calendar.DATE);

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                final View mView = getLayoutInflater().inflate(R.layout.ac_notepreview,null);
                TextView tv_notePreviewTitle = (TextView)mView.findViewById(R.id.tv_notePreviewTitle);
                RecyclerView rv_notePreviewContent = mView.findViewById(R.id.rv_notePreviewContent);

                mContentData = new ArrayList<>();

                font= Typeface.createFromAsset(getActivity().getAssets(),"fonts/CookieRegular.ttf");
                tv_notePreviewTitle.setTypeface(font);


                if(cursor.moveToFirst()){
                    while(cursor.moveToNext()){
                        if(year == cursor.getInt(1) && day == cursor.getInt(3) && (month+1) == cursor.getInt(2)){
                            //tvNote.setText(cursor.getString(5));
                            mContentData.add(new WeeklyContentItem(cursor.getString(5)));
                        }
                    }
                }

                if(mContentData.size()==0){
                    mContentData.add(new WeeklyContentItem("no event"));
                }

                tv_notePreviewTitle.setText(Integer.toString(year)+"."
                        +Integer.toString(month+1)+"."
                        +Integer.toString(day));

                monthlyContentAdapter= new MonthlyContentAdapter(getActivity(),mContentData);
                rv_notePreviewContent.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv_notePreviewContent.setAdapter(monthlyContentAdapter);


                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();


            }
        });

        return view;

    }
}
