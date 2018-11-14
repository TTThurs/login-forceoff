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

    private Button force;           //强制离线按钮
    private Button create;          //创造数据库按钮
    private Button add;             //添加图书数据按钮
    private Button update;          //更新图书数据按钮
    private Button delete;          //删除图书数据按钮
    private Button querry;          //查找图书数据按钮
    private Book book;
    private ListView view;          //图书显示ListVies
    private List<Book> list;        //数据库中图书列表
    private Button display;         //数据显示按钮
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //强制下线功能实现
        force=(Button) findViewById(R.id.force);
        force.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("com.xiaomao.xiaogou.xiaohuli");
                sendBroadcast(intent);
            }
        });

        //数据库创建实现
        create=(Button) findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
                Toast.makeText(MainActivity.this,"数据库创建成功",Toast.LENGTH_SHORT).show();
            }
        });

        //添加数据实现，另起一个活动，添加完成后返回MainActivity
        add=(Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        //更新数据功能实现，另起一个活动，更新完成后返回MainActivity
        update=(Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, UpdateActivity.class);
                startActivity(intent);
            }
        });

        //数据展示功能实现
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
