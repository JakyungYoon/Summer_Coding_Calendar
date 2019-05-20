package jakyungyoon.jacalendar.calendar.jaycalendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class DailyContentAdapter extends  RecyclerView.Adapter<DailyContentAdapter.DailyViewHolder>{

    Context mContext;
    List<WeeklyContentItem> list;
    Typeface font;
    public DailyContentAdapter(Context mContext, List<WeeklyContentItem> list) {
        this.mContext = mContext;
        this.list = list;
        for(int i =0;i<list.size();i++){
            Log.d("get",list.get(i).getContent()+"");
        }
    }

    @NonNull
    @Override
    public DailyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.rvitem_day,viewGroup,false);

        return new DailyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyViewHolder dailyViewHolder, int position) {

        WeeklyContentItem wci = list.get(position);
        String content = wci.getContent();
        dailyViewHolder.tv_dayContent.setText(wci.getContent());


        if(!content.equals("no event")){
            dailyViewHolder.tv_dayContent.setText(content);
        }else{
            dailyViewHolder.tv_dayContent.setText(content);
            dailyViewHolder.tv_dayContent.setTextColor(Color.rgb(193,	199,	205));
            dailyViewHolder.rl_dayContent.setBackgroundColor(Color.rgb(	247,	248,	249));
            dailyViewHolder.rl_dayContent.setPadding(0,0,0,5);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DailyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_dayContent;
        RelativeLayout rl_dayContent;
        public DailyViewHolder(@NonNull View itemView) {
            super(itemView);
            font= Typeface.createFromAsset(mContext.getAssets(),"fonts/CookieRegular.ttf");
            tv_dayContent = itemView.findViewById(R.id.tv_dayContent);
            tv_dayContent.setTypeface(font);
            rl_dayContent = itemView.findViewById(R.id.rl_dayContent);
        }
    }
}
