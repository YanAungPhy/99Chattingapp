package com.chatapp.nineninechatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chatapp.nineninechatapp.R;
import com.chatapp.nineninechatapp.Utils.Utility;

public class RegisterActivity extends AppCompatActivity {

    TextView txtSingIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        initView();
        initEvent();
    }

    private void initView() {
        txtSingIn = findViewById(R.id.txt_SignIn);
    }

    private void initEvent() {
        Utility.darkMode(this);
        txtSingIn.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        });
    }
}