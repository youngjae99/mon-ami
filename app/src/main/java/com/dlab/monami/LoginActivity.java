package com.dlab.monami;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {


    EditText usernameET,passwordET;

    ArrayList<String> data;
    private FirebaseAuth mAuth;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        data=new ArrayList<String>();
        usernameET=(EditText) findViewById(R.id.userid);
        passwordET=(EditText) findViewById(R.id.password);


        if(getIntent().getExtras() != null){
            EditText username = (EditText)findViewById(R.id.userid);
            Intent signupIntent = getIntent();
            username.setText(signupIntent.getStringExtra("Username"));
        }

        TextView login = findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    login();
            }
        });

        TextView signup = (TextView)findViewById(R.id.findidpw);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "서비스 준비중입니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void login() {
        String email = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        Log.d("LoginActivity", "id:" + email + " pw:" + password);
        mAuth = FirebaseAuth.getInstance();

        if (email.length() > 0 && password.length() > 0) {
//            final RelativeLayout loaderLayout = findViewById(R.id.loaderLyaout);
//            loaderLayout.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //loaderLayout.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "로그인에 성공하였습니다", Toast.LENGTH_SHORT).show();
                                Intent gotomain = new Intent(LoginActivity.this, MainActivity.class);
                                gotomain.putExtra("email", email);
                                startActivity(gotomain);
                            } else {
                                if (task.getException() != null) {
                                    Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        } else {
            Toast.makeText(LoginActivity.this, "이메일 또는 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }
    }
}