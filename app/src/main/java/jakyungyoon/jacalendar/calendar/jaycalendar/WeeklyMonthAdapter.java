package jakyungyoon.jacalendar.calendar.jaycalendar;

import android.content.Context;
import android.content.res.Resources;
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

public class WeeklyMonthAdapter extends RecyclerView.Adapter<WeeklyMonthAdapter.WeeklyMonthViewHolder> {
    Context mContext;
    List<WeeklyMonthItem> mData;
    List<WeeklyWeekItem> mWeekData;
    Typeface font ;
    String[] monthsLastday ={"20190131","20190228","20190331","20190430","20190531",
            "20190630","20190731","20190831","20190930","20191031","20191130","20191231"};
    //String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nev","Dec"};
    Date startDate, endDate;
    WeeklyWeekAdapter weeklyWeekAdapter;

    public WeeklyMonthAdapter(Context mContext, List<WeeklyMonthItem> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @NonNull
    @Override
    public WeeklyMonthViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.rvitem_weekmonth,viewGroup,false);

        return new WeeklyMonthAdapter.WeeklyMonthViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyMonthViewHolder weeklyMonthViewHolder, int position) {

        WeeklyMonthItem wmi= mData.get(position);

        String month = wmi.getMonth();
        int monthNum = wmi.getMonthNum();
        weeklyMonthViewHolder.tv_weekMonth.setText(month);


        String endD =  monthsLastday[monthNum];
        String startD ;
        if(monthNum < 9)
            startD= "20190"+(monthNum+1)+"01";
        else
            startD = "2019"+(monthNum+1)+"01";

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

        mWeekData = new ArrayList<>();

        while(startDate.compareTo(endDate)<=0){
            Calendar cal = Calendar.getInstance();
            Calendar cal6 = Calendar.getInstance();
            cal.setTime(startDate);
            cal6.setTime(startDate);
            cal6.add(Calendar.DATE,6);
            mWeekData.add(new WeeklyWeekItem(cal.get(Calendar.MONTH),cal.get(Calendar.DATE),cal.get(Calendar.WEEK_OF_MONTH),
                    cal6.get(Calendar.MONTH),cal6.get(Calendar.DATE)));

            cal.add(Calendar.DATE,7);

            startDate = cal.getTime();
        }

        for(WeeklyWeekItem wwi : mWeekData)
            Log.d("dd : "+(monthNum+1),wwi.getMonth()+" "+ wwi.getDate()+" "+ wwi.getDayOfWeekInMonth());

        weeklyWeekAdapter= new WeeklyWeekAdapter(mContext,mWeekData);


        weeklyMonthViewHolder.rv_weekWeek.setLayoutManager(new LinearLayoutManager(mContext
                ,LinearLayoutManager.VERTICAL,false));

        weeklyMonthViewHolder.rv_weekWeek.setAdapter(weeklyWeekAdapter);

        Calendar call = Calendar.getInstance();

        weeklyMonthViewHolder.rv_weekWeek.scrollToPosition(call.get(Calendar.WEEK_OF_MONTH));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class WeeklyMonthViewHolder extends RecyclerView.ViewHolder{
        TextView tv_weekMonth;
        RecyclerView rv_weekWeek;
        public WeeklyMonthViewHolder(@NonNull View itemView) {
            super(itemView);

            font= Typeface.createFromAsset(mContext.getAssets(),"fonts/CookieRegular.ttf");
            tv_weekMonth = itemView.findViewById(R.id.tv_weekMonth);
            rv_weekWeek = itemView.findViewById(R.id.rv_weekWeek);
            tv_weekMonth.setTypeface(font);

        }
    }
}
