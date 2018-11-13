package com.example.administrator.androidtest262;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    private Button force;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        force=(Button) findViewById(R.id.force);
        force.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("com.xiaomao.xiaogou.xiaohuli");
                sendBroadcast(intent);
            }
        });
    }
}
