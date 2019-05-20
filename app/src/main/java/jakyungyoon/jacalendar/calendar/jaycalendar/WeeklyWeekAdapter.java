package jakyungyoon.jacalendar.calendar.jaycalendar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WeeklyWeekAdapter extends RecyclerView.Adapter<WeeklyWeekAdapter.WeeklyWeekViewHolder> {
    Context mContext;
    List<WeeklyWeekItem> mData;
    List<WeeklyDayContentItem> mDaycontentData;
    Typeface font;
    Date startDate,endDate;
    WeeklyDayApapter weeklyDayAdapter;

    CalendarDBhelper mCalendarDBhelper;
    SQLiteDatabase db;
    Cursor cursor;
    String[] days = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
    String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nev","Dec"};

    public WeeklyWeekAdapter(Context mContext, List<WeeklyWeekItem> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @NonNull
    @Override
    public WeeklyWeekViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.rvitem_weekweek,viewGroup,false);

        return new WeeklyWeekAdapter.WeeklyWeekViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyWeekViewHolder weeklyWeekViewHolder, int position) {

        WeeklyWeekItem wwI = mData.get(position);
        mDaycontentData = new ArrayList<>();
        mCalendarDBhelper = new CalendarDBhelper(mContext);
        db = mCalendarDBhelper.getWritableDatabase();

        int month = wwI.getMonth()+1;
        int date = wwI.getDate();
        int month6= wwI.getMonth6()+1;
        int date6 = wwI.getDate6();


        if(month != month6){
            weeklyWeekViewHolder.tv_weekWeek.setText(months[month-1]+" "+Integer.toString(date)+ " - "
                +months[month6-1]+" "+Integer.toString(date6));
        } else{
            weeklyWeekViewHolder.tv_weekWeek.setText(months[month-1]+" "+Integer.toString(date)+ " - "
                    +Integer.toString(date6));

        }

        String startD;
        String endD;
        if(month<10){
            startD = "20190"+Integer.toString(month)+Integer.toString(date);
            endD = "20190"+Integer.toString(month6)+Integer.toString(date6);
        }else{
            startD = "2019"+Integer.toString(month)+Integer.toString(date);
            endD = "2019"+Integer.toString(month6)+Integer.toString(date6);
        }


        Log.d("when sat"+(month+1),startD +"//"+endD);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        try {
            startDate = sdf.parse(startD);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDate = sdf.parse(endD);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("when "+(month+1),startDate.toString() +"//"+endDate.toString());

            while(startDate.compareTo(endDate)<=0){

                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);

                mDaycontentData.add(new WeeklyDayContentItem((cal.get(Calendar.MONTH)+1) ,cal.get(Calendar.DATE),
                        days[cal.get(Calendar.DAY_OF_WEEK)-1]));
                /*//weekCalendarList.add(sdf.format(startDate));
                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);
                cursor = db.rawQuery("select content from calendar_table where month = "+ (cal.get(Calendar.MONTH)+1)
                        +" and day = " + cal.get(Calendar.DATE),null);

                if(cursor.moveToFirst()) {
                    do{
                        if (cursor != null && cursor.getCount() != 0) {
                            mDaycontentData.add(new WeeklyDayContentItem((cal.get(Calendar.MONTH)+1) ,cal.get(Calendar.DATE),
                                    days[cal.get(Calendar.DAY_OF_WEEK)-1],cursor.getString(0)));

                        }
                    }while (cursor.moveToNext() != false);
                }*/
                cal.add(Calendar.DATE, 1);
                startDate = cal.getTime();

                //cursor.close();
            }




        weeklyDayAdapter= new WeeklyDayApapter(mContext,mDaycontentData);


        weeklyWeekViewHolder.rv_weekDay.setLayoutManager(new LinearLayoutManager(mContext
                ,LinearLayoutManager.VERTICAL,false));

        weeklyWeekViewHolder.rv_weekDay.setAdapter(weeklyDayAdapter);

        Calendar call = Calendar.getInstance();

        weeklyWeekViewHolder.rv_weekDay.scrollToPosition(call.get(Calendar.DATE));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class WeeklyWeekViewHolder extends RecyclerView.ViewHolder{
        TextView tv_weekWeek;
        RecyclerView rv_weekDay;
        public WeeklyWeekViewHolder(@NonNull View itemView) {
            super(itemView);
            font= Typeface.createFromAsset(mContext.getAssets(),"fonts/CookieRegular.ttf");
            tv_weekWeek = itemView.findViewById(R.id.tv_weekWeek);
            tv_weekWeek.setTypeface(font);
            rv_weekDay = itemView.findViewById(R.id.rv_weekDay);
        }
    }
}
