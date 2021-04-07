package com.android.everyoneoncampus.view.writepersoninfo;

import com.android.everyoneoncampus.model.LabelAll;

import java.util.List;

public interface PersoninfoViewInterface {
    //设置标题和内容
    void setTitleContent(List<String> labelType, LabelAll labelName,List<String> selectedLabel);
}
