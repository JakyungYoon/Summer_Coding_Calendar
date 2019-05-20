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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeeklyDayApapter extends RecyclerView.Adapter<WeeklyDayApapter.WeeklyDayViewHolder> {
    Context mContext;
    List<WeeklyDayContentItem> mData;
    Typeface font;
    Date startDate;

    List<WeeklyContentItem> mContentData;
    WeeklyContentAdapter weeklyContentAdapter;


    CalendarDBhelper mCalendarDBhelper;
    SQLiteDatabase db;
    Cursor cursor;

    public WeeklyDayApapter(Context mContext, List<WeeklyDayContentItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public WeeklyDayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.rvitem_weekday,viewGroup,false);

        return new WeeklyDayApapter.WeeklyDayViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyDayViewHolder weeklyDayContentViewHolder, int position) {
        WeeklyDayContentItem wdci = mData.get(position);
        int month = wdci.getMonth();
        int date = wdci.getDay();

        weeklyDayContentViewHolder.tv_weekDayOfWeek.setText(mData.get(position).getDayOfWeek());
        weeklyDayContentViewHolder.tv_weekDayNum.setText(Integer.toString(mData.get(position).getDay()));

        String startD;

        if(month<10){
            startD = "20190"+Integer.toString(month)+Integer.toString(date);
        }else{
            startD = "2019"+Integer.toString(month)+Integer.toString(date);

        }


       // Log.d("when sat"+(month+1),startD +"//"+endD);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        try {
            startDate = sdf.parse(startD);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

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

        weeklyContentAdapter= new WeeklyContentAdapter(mContext,mContentData);


        weeklyDayContentViewHolder.rv_weekContent.setLayoutManager(new LinearLayoutManager(mContext
                ,LinearLayoutManager.VERTICAL,false));

        weeklyDayContentViewHolder.rv_weekContent.setAdapter(weeklyContentAdapter);



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class WeeklyDayViewHolder extends RecyclerView.ViewHolder{

        TextView tv_weekDayOfWeek,tv_weekDayNum;
        RecyclerView rv_weekContent;

        public WeeklyDayViewHolder(@NonNull View itemView) {
            super(itemView);
            font= Typeface.createFromAsset(mContext.getAssets(),"fonts/CookieRegular.ttf");
            tv_weekDayOfWeek = itemView.findViewById(R.id.tv_weekDayOfWeek);
            tv_weekDayNum = itemView.findViewById(R.id.tv_weekDayNum);
            rv_weekContent = itemView.findViewById(R.id.rv_weekContent);
            tv_weekDayNum.setTypeface(font);
            tv_weekDayOfWeek.setTypeface(font);

        }
    }
}
