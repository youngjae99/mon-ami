package com.dlab.monami;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SideBarActivity extends AppCompatActivity {

    TextView userEmail_tv;
    String user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_bar);

        Log.d("SideBarActivity", "오긴오냐");
        LinearLayout linearLayout = findViewById(R.id.aboutDlab);
        TextView dlab_tv = findViewById(R.id.dlab_tv);
        linearLayout.setOnClickListener(new LinearLayout.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ll clicked", "ll clicked");
                Intent aboutDlab = new Intent(SideBarActivity.this, AboutDlabPopUp.class);
                startActivity(aboutDlab);
            }
        });
        dlab_tv.setOnClickListener(new LinearLayout.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tv clicked", "tv clicked");
                Intent aboutDlab = new Intent(SideBarActivity.this, AboutDlabPopUp.class);
                startActivity(aboutDlab);
            }
        });
    }
}