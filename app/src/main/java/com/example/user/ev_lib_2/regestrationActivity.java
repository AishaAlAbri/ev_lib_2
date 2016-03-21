package com.example.user.ev_lib_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
public class regestrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration);
    }
    public void login_member(View view){
        Intent i = new Intent(regestrationActivity.this , Login.class);
        startActivity(i);
    }
}
