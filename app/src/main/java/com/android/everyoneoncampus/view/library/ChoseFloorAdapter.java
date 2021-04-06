package com.android.everyoneoncampus.view.library;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.allinterface.DataListener;

import java.util.List;

public class ChoseFloorAdapter extends RecyclerView.Adapter<ChoseFloorAdapter.ViewHolder> {
    private Context mContext;
    DataListener<Integer> onClickFloor;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtChooseFloor;
        View mView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView.findViewById(R.id.view_floor_choose);
            mTxtChooseFloor = itemView.findViewById(R.id.txt_choose_floor);
        }
    }

    private List<String> mFloorNameList;
    private RecyclerView mRecyclerView;
    /**
     * 设置的页码
     */
    private int currentPage = 0;

    public ChoseFloorAdapter(Context context, List<String> list, RecyclerView recyclerView){
        mFloorNameList = list;
        mRecyclerView = recyclerView;
        mContext = context;
    }

    /**
     * @param position viewpager的位置参数
     * 根据的viewpager的页数设置recylerview
     */
    public void setCurrentPage(int position){
        if(currentPage >= 0){
            deSelect(currentPage);
        }

        View view = mRecyclerView.getLayoutManager().findViewByPosition(position);
        TextView tv = view.findViewById(R.id.txt_choose_floor);
        View v = view.findViewById(R.id.view_floor_choose);
        setPageTextSelect(tv,v);

        currentPage = position;
    }

    private void deSelect(int position) {
        View view = mRecyclerView.getLayoutManager().findViewByPosition(position);
        if(view != null){
            TextView tv = view.findViewById(R.id.txt_choose_floor);
            View v = view.findViewById(R.id.view_floor_choose);
            setPageTextUnSelect(tv,v);
        }
        currentPage = - 1;
    }


    public void setPageTextSelect(TextView tv,View v){
        tv.setTextColor(ContextCompat.getColor(mContext,R.color.btn_green_color));
        v.setVisibility(View.VISIBLE);
    }

    public void setPageTextUnSelect(TextView tv,View v){
        tv.setTextColor(Color.BLACK);
        v.setVisibility(View.GONE);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_floor,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTxtChooseFloor.setText(mFloorNameList.get(position));

        if(position == currentPage){
            setPageTextSelect(holder.mTxtChooseFloor,holder.mView);
        }else{
            setPageTextUnSelect(holder.mTxtChooseFloor,holder.mView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickFloor.onComplete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFloorNameList.size();
    }

}
