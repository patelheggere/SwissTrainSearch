package patelheggere.trainsearch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import patelheggere.trainsearch.R;
import patelheggere.trainsearch.model.FromTo;


/**
 * Created by patel Heggere on 2/4/2018.
 */

public class FavouriteAdpter extends RecyclerView.Adapter<FavouriteAdpter.ViewHolder>
{
    public Context mContext;
    public List<FromTo> fromTos;

    public FavouriteAdpter(Context mContext, List<FromTo> fromTos)
    {
        this.mContext = mContext;
        this.fromTos = fromTos ;
    }

    @Override
    public FavouriteAdpter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favourite_list, parent, false);
        return new FavouriteAdpter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavouriteAdpter.ViewHolder holder, int position)
    {
        FromTo frmto = fromTos.get(position);
        if(fromTos.size()!=0 && frmto!=null)
        {
            if(frmto.getFrom().getStation().getName()!=null)
                holder.mDptName.setText(frmto.getFrom().getStation().getName());
            if(frmto.getFrom().getDeparture()!=null)
                holder.mTimePlat.setText(frmto.getFrom().getDeparture());
            if(frmto.getFrom().getPlatform()!=null)
                holder.mTimePlat.setText(holder.mTimePlat.getText()+"\nPlatform:"+frmto.getFrom().getPlatform());
            if(frmto.getTo().getStation().getName()!=null)
                holder.mArrivalName.setText(frmto.getTo().getStation().getName());
            if(frmto.getTo().getArrival()!=null)
                holder.mArrivalTime.setText(frmto.getTo().getArrival());
            if(frmto.getTo().getPlatform()!=null)
                holder.mArrivalTime.setText(holder.mArrivalTime.getText()+"\nPlatform:"+frmto.getTo().getPlatform());
        }

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return fromTos.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView mDptName;
        private TextView mTimePlat;
        private TextView mArrivalName;
        private TextView mArrivalTime;

        public ViewHolder(View v) {
            super(v);
            mDptName = v.findViewById(R.id.tvTitle);
            mTimePlat = v.findViewById(R.id.tvCategoryNameDate);
            mArrivalName = v.findViewById(R.id.tvDescription);
            mArrivalTime = v.findViewById(R.id.textView7);
        }
    }
}
