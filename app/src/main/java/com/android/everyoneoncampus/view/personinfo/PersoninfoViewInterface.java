package com.android.everyoneoncampus.view.personinfo;

import com.android.everyoneoncampus.model.LabelAll;

import java.util.List;
import java.util.Map;

public interface PersoninfoViewInterface {
    //设置标题和内容
    void setTitleContent(List<String> labelType, LabelAll labelName,List<String> selectedLabel);
}
