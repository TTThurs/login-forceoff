package com.example.administrator.androidtest262;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends BaseActivity {

    private CheckBox name_update;
    private CheckBox author_update;
    private CheckBox price_update;
    private CheckBox pages_update;
    private CheckBox press_update;

    private Button sure;

    private EditText name_change;
    private EditText author_change;
    private EditText price_change;
    private EditText pages_change;
    private EditText press_change;

    private Button commit_update;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_update=(CheckBox) findViewById(R.id.update_name);
        name_change=(EditText) findViewById(R.id.change_name);

        author_update=(CheckBox) findViewById(R.id.update_author);
        author_change=(EditText) findViewById(R.id.change_author);

        price_update=(CheckBox) findViewById(R.id.update_price);
        price_change=(EditText) findViewById(R.id.change_price);

        pages_update=(CheckBox) findViewById(R.id.update_pages);
        pages_change=(EditText) findViewById(R.id.change_pages);

        press_update=(CheckBox) findViewById(R.id.update_press);
        press_change=(EditText) findViewById(R.id.change_press);

        sure=(Button) findViewById(R.id.sure);
        commit_update=(Button) findViewById(R.id.commit_update);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=0;
                if(name_update.isChecked()){
                    name_change.setVisibility(View.VISIBLE);
                    i++;
                }
                if(author_update.isChecked()){
                    author_change.setVisibility(View.VISIBLE);
                    i++;
                }
                if(price_update.isChecked()){
                    price_change.setVisibility(View.VISIBLE);
                    i++;
                }
                if(pages_update.isChecked()){
                    pages_change.setVisibility(View.VISIBLE);
                    i++;
                }
                if(press_update.isChecked()){
                    press_change.setVisibility(View.VISIBLE);
                    i++;
                }
                if(i>0){
                    commit_update.setVisibility(View.VISIBLE);
                    Toast.makeText(UpdateActivity.this,"请在下方输入修改内容",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(UpdateActivity.this,"您什么都没选额",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commit_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                if(name_change.getVisibility()==View.VISIBLE){
                    if(!TextUtils.isEmpty(name_change.getText().toString())){
                        book.setName(name_change.getText().toString());
                    }
                    else{
                        Toast.makeText(UpdateActivity.this,"名字输入无效，将返回",Toast.LENGTH_SHORT).show();
                        UpdateActivity.this.finish();
                    }
                }
                if(author_change.getVisibility()==View.VISIBLE){
                    if(!TextUtils.isEmpty(author_change.getText().toString())){
                        book.setAuthor(author_change.getText().toString());
                    }
                    else {
                        Toast.makeText(UpdateActivity.this,"作者输入无效，将返回",Toast.LENGTH_SHORT).show();
                        UpdateActivity.this.finish();
                    }
                }
                if(price_change.getVisibility()==View.VISIBLE){
                    if(!TextUtils.isEmpty(price_change.getText().toString())){
                        try {
                            book.setPrice(Double.parseDouble(price_change.getText().toString()));
                        }
                        catch (Exception e){
                            Toast.makeText(UpdateActivity.this, "价格输入不规范",Toast.LENGTH_SHORT).show();
                            UpdateActivity.this.finish();
                        }
                    }
                    else {
                        Toast.makeText(UpdateActivity.this,"价格输入无效，将返回",Toast.LENGTH_SHORT).show();
                        UpdateActivity.this.finish();
                    }
                }
                if(pages_change.getVisibility()==View.VISIBLE){
                    if(!TextUtils.isEmpty(pages_change.getText().toString())){
                        try {
                            book.setPages(Integer.parseInt(pages_change.getText().toString()));
                        }
                        catch (Exception e){
                            Toast.makeText(UpdateActivity.this,"页数输入无效",Toast.LENGTH_SHORT).show();
                            UpdateActivity.this.finish();
                        }
                    }
                    else {
                        Toast.makeText(UpdateActivity.this,"页数输入无效，将返回",Toast.LENGTH_SHORT).show();
                        UpdateActivity.this.finish();
                    }
                }
                if(press_change.getVisibility()==View.VISIBLE){
                    if(!TextUtils.isEmpty(price_change.getText().toString())){
                        book.setPress(press_change.getText().toString());
                    }
                    else {
                        Toast.makeText(UpdateActivity.this,"出版社输入无效，将返回",Toast.LENGTH_SHORT).show();
                        UpdateActivity.this.finish();
                    }
                }
                book.updateAll();           //默认更新全部，可以进行扩展，更新部分数据
                UpdateActivity.this.finish();
            }
        });
    }
}
