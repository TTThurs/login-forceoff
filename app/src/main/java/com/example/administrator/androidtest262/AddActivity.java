package com.example.administrator.androidtest262;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends BaseActivity {
    private Button commit;
    private Book book;
    private EditText book_name;
    private EditText book_author;
    private EditText book_price;
    private  EditText book_pages;
    private EditText book_press;
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_add);
        book_name=(EditText) findViewById(R.id.book_name);
        book_author=(EditText) findViewById(R.id.book_author);
        book_price=(EditText) findViewById(R.id.book_price);
        book_pages=(EditText) findViewById(R.id.book_pages);
        book_press=(EditText) findViewById(R.id.book_press);

        commit=(Button) findViewById(R.id.commit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(book_name.getText().toString())||TextUtils.isEmpty(book_author.getText().toString())||TextUtils.isEmpty(book_pages.getText().toString())||TextUtils.isEmpty(book_press.getText().toString())||TextUtils.isEmpty(book_price.getText().toString())){
                    Toast.makeText(AddActivity.this, "输入不能为空额，将返回添加页面",Toast.LENGTH_SHORT).show();
                    AddActivity.this.finish();;
                }
                else {
                    book = new Book();
                    book.setName(book_name.getText().toString());
                    book.setAuthor(book_author.getText().toString());
                    try {
                        book.setPrice(Double.parseDouble(book_price.getText().toString()));
                        book.setPages(Integer.parseInt(book_pages.getText().toString()));
                    }catch (Exception e){
                        Toast.makeText(AddActivity.this,"输入格式不对额",Toast.LENGTH_SHORT).show();
                        AddActivity.this.finish();
                    }
                    book.setPress((book_press.getText().toString()));
                    book.save();
                    Toast.makeText(AddActivity.this,"数据添加成功",Toast.LENGTH_SHORT).show();
                    AddActivity.this.finish();
                }
            }
        });
    }
}
