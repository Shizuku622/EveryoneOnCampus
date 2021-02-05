package com.android.everyoneoncampus.model;

import android.provider.Telephony;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LabelAll {
//    class TypeName{
//        String type = "";
//        List<String> name = new ArrayList<>();
//    }

    private List<Map<String,List<String>>> typeNameList = new ArrayList<>();

    public void  addLabel(String type,String name){

        if(typeNameList.size() == 0 || !checkType(type)){
//            TypeName typeName = new TypeName();
//            typeName.type = type;
            Map<String,List<String>> mapLabel = new HashMap<>();
            List<String> tempname = new ArrayList<>();
            mapLabel.put(type,tempname);
            typeNameList.add(mapLabel);
        }

        for(int i = 0;i < typeNameList.size();i++){
            if(typeNameList.get(i).containsKey(type)){
                typeNameList.get(i).get(type).add(name);
            }
        }
    }

    public List<String> getLabelName(String type){
        List<String> nameList = new ArrayList<>();
        for(int i = 0;i < typeNameList.size();i++){
            if(typeNameList.get(i).containsKey(type)){
                nameList.addAll(typeNameList.get(i).get(type));
            }
        }
        return nameList;
    }


    private boolean checkType(String type){
        boolean flag = false;
        for(int i = 0;i < typeNameList.size();i++){
            if(typeNameList.get(i).containsKey(type)){
                flag = true;
            }
        }
        return flag;
    }


}
