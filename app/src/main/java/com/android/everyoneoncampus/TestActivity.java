package com.android.everyoneoncampus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.allinterface.ReturnSQL;
import com.android.everyoneoncampus.model.MySQLModel;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;

public class TestActivity extends AppCompatActivity {

    MySQLModel mMySQLModel = new MySQLModel();
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        AVIMClient tom = AVIMClient.getInstance("Tom");
//        tom.open(new AVIMClientCallback() {
//            @Override
//            public void done(AVIMClient client, AVIMException e) {
//                if(e == null){
//                    Toast.makeText(TestActivity.this, "成功连接 ", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }
}