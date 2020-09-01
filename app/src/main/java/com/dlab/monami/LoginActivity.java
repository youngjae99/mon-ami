package com.dlab.monami;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private DatabaseReference mPostReference;
    String uid="", pw="";
    EditText usernameET,passwordET;

    ArrayList<String> data;
    ArrayAdapter<String> arrayAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        data=new ArrayList<String>();
        usernameET=(EditText) findViewById(R.id.userid);
        passwordET=(EditText) findViewById(R.id.password);

        mPostReference= FirebaseDatabase.getInstance().getReference();

        if(getIntent().getExtras() != null){
            EditText username = (EditText)findViewById(R.id.userid);
            Intent signupIntent = getIntent();
            username.setText(signupIntent.getStringExtra("Username"));
        }

        TextView login = findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uid=usernameET.getText().toString();
                pw=passwordET.getText().toString();
                /*
                DatabaseReference idRef = FirebaseDatabase.getInstance().getReference("id_list");
                DatabaseReference usernameRef = idRef.child(uid);
                DatabaseReference passwordRef = usernameRef.child("signupPassword");

                passwordRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Log.i(TAG, dataSnapshot.getValue(String.class));
                        String getfullname=dataSnapshot.getValue(String.class);
                        if(getfullname.equals(pw)==true){
                            EditText username = (EditText)findViewById(R.id.userid);
                            Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                            loginIntent.putExtra("Username", username.getText().toString());
                            startActivity(loginIntent);
                        }else{
                            Toast.makeText(LoginActivity.this,"Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //Log.w(TAG, "onCancelled", databaseError.toException());
                    }
                });*/
                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                //loginIntent.putExtra("Username", username.getText().toString());
                SaveSharedPreferences.setUserName(LoginActivity.this, usernameET.getText().toString());
                startActivity(loginIntent);
            }
        });

        TextView signup = (TextView)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(signupIntent);
            }
        });
    }
}