package com.android.everyoneoncampus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.everyoneoncampus.model.MySQLModel;

import java.sql.Connection;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        MySQLModel mySQLModel = new MySQLModel();
        new Thread(()->{
            Connection conn = mySQLModel.getConnector();
            if(conn != null){
//                Toast.makeText(this,"不为空",Toast.LENGTH_LONG).show();
            }
        }).start();
    }
}