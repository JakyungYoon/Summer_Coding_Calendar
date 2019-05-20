package jakyungyoon.jacalendar.calendar.jaycalendar;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FragmentWeekly extends Fragment {

    View view;
    RecyclerView weeklyRecyclerview;
    //WeeklyAdapter weeklyAdapter;
    WeeklyMonthAdapter weeklyMonthAdapter;
    //List<WeeklyItem> mdata;
    List<WeeklyMonthItem> mdata;

    CalendarDBhelper mCalendarDBhelper;
    SQLiteDatabase db;
    Cursor cursor;
    Date startDate;
    Date endDate;
    String[] days = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
    String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nev","Dec"};

    Typeface font;
    Date sd;

    public FragmentWeekly() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.weekly_main,container,false);

        weeklyRecyclerview = view.findViewById(R.id.rv_weeklyTotal);

        mdata = new ArrayList<>();
        mCalendarDBhelper = new CalendarDBhelper(getActivity());
        db = mCalendarDBhelper.getWritableDatabase();

        String inputStartDate = "20190101";
        String inputEndDate = "20191231";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            startDate = sdf.parse(inputStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDate = sdf.parse(inputEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }



      /*  while(startDate.compareTo(endDate)<=0){

            //weekCalendarList.add(sdf.format(startDate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cursor = db.rawQuery("select content from calendar_table where year = "+ cal.get(Calendar.YEAR)
                    + " and month = "+ (cal.get(Calendar.MONTH)+1) +" and day = " + cal.get(Calendar.DATE),null);


                if (cursor != null && cursor.getCount() != 0) {
                    if(cursor.moveToFirst())
                        mdata.add(new WeeklyItem(days[cal.get(Calendar.DAY_OF_WEEK)-1], Integer.toString(cal.get(Calendar.DATE))
                                , months[cal.get(Calendar.MONTH)],Integer.toString(cal.get(Calendar.DAY_OF_WEEK_IN_MONTH)) +" week"
                                ,cursor.getString(0)));
                } else {
                    mdata.add(new WeeklyItem(days[cal.get(Calendar.DAY_OF_WEEK)-1], Integer.toString(cal.get(Calendar.DATE))
                            , months[cal.get(Calendar.MONTH)],Integer.toString(cal.get(Calendar.DAY_OF_WEEK_IN_MONTH))+" week"
                            , "There are no schedule"));
                }

            cal.add(Calendar.DAY_OF_MONTH, 1);
            startDate = cal.getTime();
        }




        weeklyAdapter = new WeeklyAdapter(getActivity(),mdata);

        weeklyRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        weeklyRecyclerview.setAdapter(weeklyAdapter);
        weeklyRecyclerview.scrollToPosition(90);

        Log.d("isthere" , weeklyRecyclerview.getScrollState()+"");

*/



     for(int i =0;i<months.length;i++){
         mdata.add(new WeeklyMonthItem(months[i],i));
         //Log.d("ttt",mdata.get(i).getMonth()+"");
     }

      weeklyMonthAdapter= new WeeklyMonthAdapter(getActivity(),mdata);
      weeklyRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
      weeklyRecyclerview.setAdapter(weeklyMonthAdapter);

      Calendar call = Calendar.getInstance();

      weeklyRecyclerview.scrollToPosition(call.get(Calendar.MONTH));

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_week);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder mBuilder =  new AlertDialog.Builder(getActivity());
                final View mView = getLayoutInflater().inflate(R.layout.ac_calendar_addnote,null);
                final View tView = getLayoutInflater().inflate(R.layout.item_title,null);
                final TextView tvNewEvent = mView.findViewById(R.id.tvNewEvent);
                final EditText etTitle = (EditText)mView.findViewById(R.id.etTitle);
                final TextView tvYear = (TextView) mView.findViewById(R.id.tvYear);
                final Spinner spMonth = (Spinner)mView.findViewById(R.id.spinnerMonth);
                final Spinner spDay = (Spinner)mView.findViewById(R.id.spinnerDay);
                TextView title = tView.findViewById(R.id.title);

                font= Typeface.createFromAsset(getActivity().getAssets(),"fonts/CookieRegular.ttf");
                etTitle.setTypeface(font);
                tvYear.setTypeface(font);
                tvNewEvent.setTypeface(font);
                title.setTypeface(font);


                ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.Month));
                monthAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spMonth.setAdapter(monthAdapter);

                ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.Day));
                monthAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spDay.setAdapter(dayAdapter);

                //title.setText("Save");

                mBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(etTitle.getText().toString().isEmpty() ||
                                spMonth.getSelectedItem().toString().equals("Month")||
                                spDay.getSelectedItem().toString().equals("Day")
                                ){
                            Toast.makeText(getActivity(),"needs to write",Toast.LENGTH_LONG).show();

                        }else {

                            String title = etTitle.getText().toString();
                            String year = tvYear.getText().toString();
                            String month = spMonth.getSelectedItem().toString();
                            String day = spDay.getSelectedItem().toString();

                            String startD = year + month + day;

                            Log.d("startD",startD);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                            try {
                                sd = sdf.parse(startD);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Calendar cal = Calendar.getInstance();

                            cal.setTime(sd);


                            db.execSQL("INSERT INTO calendar_table VALUES(null, " + year + ", " + month + ", " +
                                    day + ", " + cal.get(Calendar.WEEK_OF_MONTH) + ", '" + title + "');");

                            weeklyMonthAdapter= new WeeklyMonthAdapter(getActivity(),mdata);
                            weeklyRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                            weeklyRecyclerview.setAdapter(weeklyMonthAdapter);

                            Calendar call = Calendar.getInstance();

                            weeklyRecyclerview.scrollToPosition(call.get(Calendar.MONTH));

                        }

                    }
                });


                // mBuilder.setTitle("New Event");
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();


            }
        });




        return view;
    }
}
