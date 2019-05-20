package jakyungyoon.jacalendar.calendar.jaycalendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MonthlyContentAdapter extends RecyclerView.Adapter<MonthlyContentAdapter.MonthlyContentViewHolder> {

    Context mContext;
    List<WeeklyContentItem> list;
    Typeface font;

    public MonthlyContentAdapter(Context mContext, List<WeeklyContentItem> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public MonthlyContentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.rvitem_monthcontent,viewGroup,false);

        return new MonthlyContentViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyContentViewHolder monthlyContentViewHolder, int position) {

        WeeklyContentItem wci = list.get(position);
        String content = wci.getContent();
      //  monthlyContentViewHolder.tv_monthContent.setText(wci.getContent());

       // monthlyContentViewHolder.tv_monthContent.setText(content);
        if(!content.equals("no event")){
            monthlyContentViewHolder.tv_monthContent.setText(content);
        }else{
            monthlyContentViewHolder.tv_monthContent.setText(content);
            monthlyContentViewHolder.tv_monthContent.setTextColor(Color.rgb(193,	199,	205));
            //monthlyContentViewHolder.tv_monthContent.setBackgroundColor(Color.rgb(	247,	248,	249));
           // monthlyContentViewHolder.tv_monthContent.setPadding(0,0,0,5);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MonthlyContentViewHolder extends RecyclerView.ViewHolder{
        TextView tv_monthContent;
        public MonthlyContentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_monthContent = itemView.findViewById(R.id.tv_monthContent);
            font= Typeface.createFromAsset(mContext.getAssets(),"fonts/CookieRegular.ttf");

            tv_monthContent.setTypeface(font);

        }
    }
}
