package com.dlab.monami;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class SideBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_bar);

        LinearLayout linearLayout = findViewById(R.id.aboutDlab);
        linearLayout.setOnClickListener(new LinearLayout.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutDlab = new Intent(getApplicationContext(), NewRecord.class);
                startActivity(aboutDlab);
            }
        });
    }
}