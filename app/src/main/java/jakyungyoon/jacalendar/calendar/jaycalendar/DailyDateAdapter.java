package jakyungyoon.jacalendar.calendar.jaycalendar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DailyDateAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater inflater;
    Typeface font;
    ArrayList<String> date = new ArrayList<String>();
    String[] dateAA;
    List<WeeklyContentItem> mContentData;
    SQLiteDatabase db;
    Cursor cursor;
    CalendarDBhelper mCalendarDBhelper;
    DailyContentAdapter dailyContentAdapter;
    Date startDatet;



    public String[] theDate() throws ParseException {
        String startD = "20190101";
        String endD = "20191231";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");;
        Date startDate = sdf.parse(startD);
        Date endDate = sdf.parse(endD);


        while(startDate.compareTo(endDate)<=0){

            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            date.add(cal.get(Calendar.YEAR)+"."+(cal.get(Calendar.MONTH)+1)+"."+cal.get(Calendar.DATE));
            cal.add(Calendar.DATE,1);
            startDate = cal.getTime();
        }
        String[] dateA = date.toArray(new String[date.size()]);



       return dateA;
    }


    public DailyDateAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 365;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(ConstraintLayout)object);
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.vpitem_day,container,false);
        //ConstraintLayout layoutslide = view.findViewById(R.id.cl_day);
        TextView tv_dayDate = view.findViewById(R.id.tv_dayDate);
        RecyclerView rc_dayContent = view.findViewById(R.id.rv_dayContent);

        try {
            dateAA = theDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String startD = dateAA[position];

        Intent intent = new Intent(mContext, FragmentDay.class);
        intent.putExtra(startD,"ddd");
        font= Typeface.createFromAsset(mContext.getAssets(),"fonts/CookieRegular.ttf");

        tv_dayDate.setText(startD);
        tv_dayDate.setTypeface(font);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        try {
            startDatet = sdf.parse(startD);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDatet);

        mContentData = new ArrayList<>();
        mCalendarDBhelper = new CalendarDBhelper(mContext);
        db = mCalendarDBhelper.getWritableDatabase();
        //weekCalendarList.add(sdf.format(startDate));

        cursor = db.rawQuery("select content from calendar_table where month = "+ (cal.get(Calendar.MONTH)+1)
                +" and day = " + cal.get(Calendar.DATE),null);

        if(cursor.moveToFirst()) {
            do{
                if (cursor != null && cursor.getCount() != 0) {
                    mContentData.add(new WeeklyContentItem(cursor.getString(0)));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();

        if(mContentData.size()==0){
            mContentData.add(new WeeklyContentItem("no event"));
        }


        for(int i =0;i<mContentData.size();i++){
            Log.d("Dddd",mContentData.get(i).getContent()+" ");
        }

        dailyContentAdapter= new DailyContentAdapter(mContext,mContentData);
        rc_dayContent.setLayoutManager(new LinearLayoutManager(mContext));
        rc_dayContent.setAdapter(dailyContentAdapter);


        container.addView(view);

        return view;

    }
}
