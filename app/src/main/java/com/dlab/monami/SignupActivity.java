package com.dlab.monami;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {
    private DatabaseReference mPostReference;
    String uid="", pw="";

    ArrayList<String> data;
    ArrayAdapter<String> arrayAdapter;

    TextView signupbtn;

    String TAG="signupActivity";

    // google -------------------------------------------------------
    public static final String GOOGLE_ACCOUNT = "google_account";
    private TextView nameET, emailET, passwordET, passwordconfirmET;
    private GoogleSignInAccount mGoogleSignInAccount;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth;


    // -------------------------------------------------------
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //파이어베이스 접근 설정
        // user = firebaseAuth.getCurrentUser();
        firebaseAuth =  FirebaseAuth.getInstance();
        //firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();

        nameET = findViewById(R.id.username);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        passwordconfirmET = findViewById(R.id.passwordETconfirm);

        mGoogleSignInAccount = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);

        //setDataOnView();

        signupbtn = findViewById(R.id.singupBtn);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpemail();
            }
        });
    }

    private void signUpemail(){
        final String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
        String passwordCheck = passwordconfirmET.getText().toString().trim();

        if (email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0) {
            if (password.equals(passwordCheck)) {
//                        final RelativeLayout loaderLayout = findViewById(R.id.loaderLyaout);
//                        loaderLayout.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                        loaderLayout.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(SignupActivity.this, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                    Intent gotomain = new Intent(SignupActivity.this, MainActivity.class);
                                    startActivity(gotomain);
                                } else {
                                    if (task.getException() != null) {
                                        Toast.makeText(SignupActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            } else {
                Toast.makeText(SignupActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(SignupActivity.this, "이메일 또는 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    /*
    private void setDataOnView() {
        // implementation 'com.squareup.picasso:picasso:2.71828'
        profileName.setText(mGoogleSignInAccount.getDisplayName());
        profileEmail.setText(mGoogleSignInAccount.getEmail());
    }*/
}
