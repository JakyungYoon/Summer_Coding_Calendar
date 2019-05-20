package jakyungyoon.jacalendar.calendar.jaycalendar;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

import com.applandeo.materialcalendarview.EventDay;

import org.w3c.dom.Text;

import java.nio.channels.InterruptedByTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class FragmentDay extends Fragment {
    View view;
    DailyDateAdapter dailyDateAdapter;
    ViewPager viewPager;
    Typeface font;
    SQLiteDatabase db;
    CalendarDBhelper mCalendarDBhelper;
    Date sd;

    public FragmentDay() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_day,container,false);

        viewPager = (ViewPager) view.findViewById(R.id.vp_day);
        dailyDateAdapter = new DailyDateAdapter(getActivity());
        mCalendarDBhelper = new CalendarDBhelper(getActivity());
        db = mCalendarDBhelper.getWritableDatabase();


        viewPager.setAdapter(dailyDateAdapter);

        Calendar cal = Calendar.getInstance();

        viewPager.setCurrentItem(cal.get(Calendar.DAY_OF_YEAR));


        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_day);
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
                            viewPager.setAdapter(dailyDateAdapter);
                            Calendar call = Calendar.getInstance();

                            viewPager.setCurrentItem(call.get(Calendar.DAY_OF_YEAR));
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
