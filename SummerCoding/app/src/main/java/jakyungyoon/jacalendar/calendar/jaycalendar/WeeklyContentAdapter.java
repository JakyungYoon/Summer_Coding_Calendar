package jakyungyoon.jacalendar.calendar.jaycalendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class WeeklyContentAdapter extends RecyclerView.Adapter<WeeklyContentAdapter.WeeklyContentViewHolder> {
    Context mContext;
    List<WeeklyContentItem> mData;
    Typeface font;

    public WeeklyContentAdapter(Context mContext, List<WeeklyContentItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public WeeklyContentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.rvitem_weekcontent,viewGroup,false);

        return new WeeklyContentAdapter.WeeklyContentViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyContentViewHolder weeklyContentViewHolder, int position) {
        WeeklyContentItem wci = mData.get(position);
        String content = wci.getContent();

        if(!content.equals("no event")){
            weeklyContentViewHolder.tv_weekContent.setText(content);
        }else{
            weeklyContentViewHolder.tv_weekContent.setText(content);
            weeklyContentViewHolder.tv_weekContent.setTextColor(Color.rgb(193,	199,	205));
            weeklyContentViewHolder.rl_weekContent.setBackgroundColor(Color.rgb(	247,	248,	249));
            weeklyContentViewHolder.rl_weekContent.setPadding(0,0,0,5);
        }

        //weeklyContentViewHolder.tv_weekContent.setText(content);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class WeeklyContentViewHolder extends RecyclerView.ViewHolder{
        TextView tv_weekContent;
        RelativeLayout rl_weekContent;
        public WeeklyContentViewHolder(@NonNull View itemView) {
            super(itemView);
            //font= Typeface.createFromAsset(mContext.getAssets(),"fonts/OpenSansCondensed-Light.ttf");
            font= Typeface.createFromAsset(mContext.getAssets(),"fonts/CookieRegular.ttf");
            tv_weekContent = itemView.findViewById(R.id.tv_weekContent);
            rl_weekContent = itemView.findViewById(R.id.rl_weekContent);
            tv_weekContent.setTypeface(font);
        }
    }

}
