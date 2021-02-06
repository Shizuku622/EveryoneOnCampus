package com.android.everyoneoncampus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.allinterface.ReturnSQL;
import com.android.everyoneoncampus.model.MySQLModel;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    MySQLModel mMySQLModel = new MySQLModel();
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = findViewById(R.id.txt33);
        mMySQLModel.getCurrentUserInfo(new DataListener<String>() {
            @Override
            public void onComplete(String result) {
                textView.setText(result);
            }
        });
    }
}