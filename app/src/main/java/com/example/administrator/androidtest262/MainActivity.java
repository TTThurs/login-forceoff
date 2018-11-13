package com.example.administrator.androidtest262;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    private Button force;
    private Button create;
    private Button add;
    private Button update;
    private Button delete;
    private Button querry;
    private Book book;
    private ListView view;
    private List<Book> list;
    private Button display;
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

        create=(Button) findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
                Toast.makeText(MainActivity.this,"数据库创建成功",Toast.LENGTH_SHORT).show();
            }
        });

        add=(Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        display=(Button) findViewById(R.id.display);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> content=new ArrayList<>();
                list=DataSupport.findAll(Book.class);
                if(list!=null){
                    for(Book bookk:list){
                        content.add(bookk.toString());
                    }
                    ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,content);
                    view=(ListView) findViewById(R.id.listview);
                    view.setAdapter(adapter);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"还没有添加数据额",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
