package com.example.administrator.androidtest262;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private IntentFilter filter;
    private MyReceiver receiver;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCollector.activities.add(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        filter=new IntentFilter();
        filter.addAction("com.xiaomao.xiaogou.xiaohuli");
        receiver=new MyReceiver();
        registerReceiver(receiver,filter);
    }

    @Override
    public void onPause(){
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        ActivityCollector.activities.remove(this);
    }

    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ActivityCollector.finishAll();
            Intent intent1=new Intent(context, LoginActivity.class);
            startActivity(intent1);
        }
    }
}
