package com.chatapp.nineninechatapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chatapp.nineninechatapp.R;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    ImageView btnShowImageHide;
    EditText edtPassword;
    TextView txtSingUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();
    }

    public void initView(){
        btnLogin = findViewById(R.id.btn_login);
        btnShowImageHide = findViewById(R.id.btn_showImageHide);
        btnShowImageHide.setImageResource(R.drawable.visibility_off);
        edtPassword = findViewById(R.id.edt_password);
        txtSingUp = findViewById(R.id.txt_SingUp);
    }

    private void initEvent() {
        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        });

        btnShowImageHide.setOnClickListener(v -> {
            if(edtPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                btnShowImageHide.setImageResource(R.drawable.visibility_off);
            }else {
                edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                btnShowImageHide.setImageResource(R.drawable.visibility_fill);
            }
        });

        txtSingUp.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
        });

    }
}