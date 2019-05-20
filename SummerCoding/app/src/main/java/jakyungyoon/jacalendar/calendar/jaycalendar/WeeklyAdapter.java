package jakyungyoon.jacalendar.calendar.jaycalendar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class WeeklyAdapter extends RecyclerView.Adapter<WeeklyAdapter.WeeklyViewHolder> {


    Context mContext;
    List<WeeklyItem> mData;


    public WeeklyAdapter(Context mContext, List<WeeklyItem> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @NonNull
    @Override
    public WeeklyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.weekly_fragment,viewGroup,false);



        return new WeeklyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyViewHolder weeklyViewHolder, int position) {

        //bind data here

        //apply animation to views here

        /*weeklyViewHolder.tv_day.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));


        weeklyViewHolder.tv_title.setText(mData.get(position).getTitle());
        weeklyViewHolder.tv_content.setText(mData.get(position).getContent());
        weeklyViewHolder.tv_date.setText(mData.get(position).getDate());*/
        weeklyViewHolder.tv_weekDayOfWeek.setText(mData.get(position).getDayOfWeek());
        weeklyViewHolder.tv_weekDayNum.setText(mData.get(position).getDayNum());
        weeklyViewHolder.tv_weekContent.setText(mData.get(position).getWeekcontent());
        weeklyViewHolder.tv_weekDayOfWeekInMonth.setText(mData.get(position).getDayOfWeekInMonth());
        weeklyViewHolder.tv_weekMonth.setText(mData.get(position).getMonth());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class WeeklyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_weekDayOfWeek, tv_weekDayNum, tv_weekContent, tv_weekMonth,tv_weekDayOfWeekInMonth;


        public WeeklyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_weekDayOfWeek = itemView.findViewById(R.id.tv_weekDayOfWeek);
            tv_weekDayNum = itemView.findViewById(R.id.tv_weekDayNum);
            tv_weekContent = itemView.findViewById(R.id.tv_weekContent);
            tv_weekMonth = itemView.findViewById(R.id.tv_weekMonth22);
            tv_weekDayOfWeekInMonth = itemView.findViewById(R.id.tv_weekDayOfWeekNum);

        }
    }



}
