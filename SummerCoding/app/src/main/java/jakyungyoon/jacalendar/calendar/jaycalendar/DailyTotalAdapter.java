package jakyungyoon.jacalendar.calendar.jaycalendar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DailyTotalAdapter extends RecyclerView.Adapter<DailyTotalAdapter.DailyTotalViewHolder> {

    Context mContext;
    List<DailyItem> mData;

    public DailyTotalAdapter(Context mContext, List<DailyItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
        }

    @NonNull
    @Override
    public DailyTotalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.vpitem_day,viewGroup,false);


        return new DailyTotalViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyTotalViewHolder dailyTotalViewHolder, int position) {



       // DailyContentAdapter dailyContentAdapter = new DailyContentAdapter(mContext,mData);

        dailyTotalViewHolder.tv_dayDate.setText(mData.get(position).getDayDate());
        //dailyTotalViewHolder.rv_dayContent.setLayoutManager(new LinearLayoutManager(mContext));
        //dailyTotalViewHolder.rv_dayContent.setAdapter(dailyContentAdapter);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class DailyTotalViewHolder extends RecyclerView.ViewHolder{
        TextView tv_dayDate;
        RecyclerView rv_dayContent;
        public DailyTotalViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_dayDate = itemView.findViewById(R.id.tv_dayDate);
            //rv_dayContent = itemView.findViewById(R.id.rv_dayContent);

        }
    }
}
