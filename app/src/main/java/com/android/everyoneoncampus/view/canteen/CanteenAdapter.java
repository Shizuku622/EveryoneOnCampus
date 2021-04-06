package com.android.everyoneoncampus.view.canteen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.model.entity.Canteen;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import java.util.List;

public class CanteenAdapter extends RecyclerView.Adapter<CanteenAdapter.ViewHolder> {
    private List<Canteen> mCanteenList;

    public CanteenAdapter(List<Canteen> list){
        mCanteenList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_canteen,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Canteen canteen = mCanteenList.get(position);
        holder.mCanteenName.setText(canteen.getCanteenName());
        holder.mCanteenAdd.setText(canteen.getCanteenAddress());
        holder.mBanCanteenPic.setAdapter(new BannerImageAdapter<Integer>(canteen.getCanteenPic()) {
            @Override
            public void onBindView(BannerImageHolder holder, Integer data, int position, int size) {
                holder.imageView.setImageResource(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCanteenList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mCanteenName;
        TextView mCanteenAdd;
        Banner mBanCanteenPic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCanteenName = itemView.findViewById(R.id.txt_canteen_name);
            mCanteenAdd = itemView.findViewById(R.id.txt_canteen_address);
            mBanCanteenPic = itemView.findViewById(R.id.ban_canteen_pic);
        }
    }
}
