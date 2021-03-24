package com.android.everyoneoncampus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;
import cn.leancloud.im.AVIMOptions;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;

public class ChatTestActivity extends BaseActivity {
    private static final String TAG = "ChatTestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_test);

        AVIMOptions.getGlobalOptions().setAutoOpen(true);
        LCChatKit.getInstance().open(EocApplication.USER_MARK + 277, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (null == e) {
                    Log.d(TAG, "登录");
                } else {

                }
            }
        });

        Button button = findViewById(R.id.btn_chat_open);
        button.setOnClickListener(v->{
            Intent intent = new Intent(ChatTestActivity.this, LCIMConversationActivity.class);
            intent.putExtra(LCIMConstants.PEER_ID, "EOC278");
            startActivity(intent);
        });
    }
}