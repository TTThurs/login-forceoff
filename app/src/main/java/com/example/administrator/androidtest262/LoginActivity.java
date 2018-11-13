package com.example.administrator.androidtest262;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    private EditText account;
    private EditText password;
    private CheckBox remember;
    private Button login;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private boolean isRemember=false;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        account=(EditText) findViewById(R.id.account);
        password=(EditText) findViewById(R.id.password);
        remember=(CheckBox) findViewById(R.id.check);
        login=(Button) findViewById(R.id.login);
        preferences=PreferenceManager.getDefaultSharedPreferences(this);
        editor=preferences.edit();

        isRemember=preferences.getBoolean("isRemember",false);
        if(isRemember){
            String userAccount=preferences.getString("account","");
            String userPassword=preferences.getString("password","");
            boolean userRemember=isRemember;

            account.setText(userAccount);
            password.setText(userPassword);
            remember.setChecked(userRemember);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(account.getText().toString().equals("admin")&&password.getText().toString().equals("123456")){
                    if(remember.isChecked()){
                        editor.putString("account", account.getText().toString());
                        editor.putString("password", password.getText().toString());
                        editor.putBoolean("isRemember", remember.isChecked());
                    }
                    else{
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, "账户或者密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
