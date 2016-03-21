package com.example.user.ev_lib_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
EditText username , passwared ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username_edtext);
        passwared=(EditText) findViewById(R.id.passwd_edtext);

    }
    public  void forgotPasswared(View view){
        Intent i = new Intent(Login.this ,forgot_passwared.class);
        startActivity(i);

    }

    public void login_onclick(View view) {
        if (username.getText().toString().equals("admin") &&

                passwared.getText().toString().equals("admin")) {

            Intent i = new Intent(Login.this ,MainActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(getApplicationContext(), "Please enter Correct pass...", Toast.LENGTH_SHORT).show();

        }

    }
}
