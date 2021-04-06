package com.android.everyoneoncampus.view.schoolactivity;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.model.entity.SchoolActive;

import java.util.List;

public class SchoolActivityAdapter extends RecyclerView.Adapter<SchoolActivityAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle;
        TextView mContent;
        ImageView mSchoolBadge;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.txt_school_activity_title);
            mContent = itemView.findViewById(R.id.txt_school_activity_content);
            mSchoolBadge = itemView.findViewById(R.id.img_school_badge);
        }
    }

    private List<SchoolActive> mSchoolActives ;

    public SchoolActivityAdapter(List<SchoolActive> list){
        mSchoolActives = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_school_activity,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTitle.setText(mSchoolActives.get(position).getTitle());
        holder.mContent.setText("        "+mSchoolActives.get(position).getContent());
        holder.mSchoolBadge.setImageResource(mSchoolActives.get(position).getSchoolBadge());
    }

    @Override
    public int getItemCount() {
        return mSchoolActives.size();
    }

}
