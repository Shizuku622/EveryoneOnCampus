package com.android.everyoneoncampus.view.writepersoninfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.everyoneoncampus.R;
import com.android.everyoneoncampus.allinterface.GetData;
import com.android.everyoneoncampus.allinterface.OperateMethod;
import com.android.everyoneoncampus.model.api.DbHelper;
import com.android.everyoneoncampus.model.api.SPModel;

import java.util.List;

public class RecLabelAdapter extends RecyclerView.Adapter<RecLabelAdapter.LableViewHolder> {

    class LableViewHolder extends RecyclerView.ViewHolder {
        public  TextView lableName ;
        public LableViewHolder(@NonNull View itemView) {
            super(itemView);
            lableName = itemView.findViewById(R.id.txt_lablename);
        }
    }

    private List<String> labelNameList;
    private List<String> selectedList;
    private OperateMethod mOperateMethod;
    private GetData<List<String>> mGetData;
    private DbHelper mDbHelper = new DbHelper();
    private SPModel spModel = new SPModel();


    public RecLabelAdapter(List<String> list, List<String> sl, OperateMethod operateMethod, GetData getData){
        this.labelNameList = list;
        this.selectedList = sl;
        this.mOperateMethod = operateMethod;
        this.mGetData = getData;
    }

    @NonNull
    @Override
    public LableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_label,parent,false);
        LableViewHolder lableViewHolder = new LableViewHolder(view);
        return lableViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LableViewHolder holder, int position) {
        holder.lableName.setText(labelNameList.get(position));
//        List<String> selectLabel = mGetData.getData();
//        for(int i = 0;i < selectLabel.size();i++){
//            if (holder.lableName.getCurrentTextColor() != Color.WHITE){
//                if(selectLabel.get(i).equals(labelNameList.get(position))){
//                    holder.lableName.setBackgroundResource(R.drawable.item_label_shape_selected);
//                    holder.lableName.setTextColor(Color.WHITE);
//                }
//            }
//
//        }
        holder.lableName.setOnClickListener(v->{
            //点击添加到数据库
            if(mDbHelper.getLabelCount() < 6) {
                mOperateMethod.onOperate(labelNameList.get(position));
            }
//            if(holder.lableName.getCurrentTextColor() != Color.WHITE){
//                holder.lableName.setBackgroundResource(R.drawable.item_label_shape_selected);
//                holder.lableName.setTextColor(Color.WHITE);
//            }

        });
    }

    @Override
    public int getItemCount() {
        return labelNameList.size();
    }

}
