package com.dlab.monami;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class RecordPopUp extends Activity {

    private TextView title;
    private TextView symptom;
    private ImageView img;
    private TextView comment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_record_pop_up);


//        title = findViewById(R.id.title);
//        symptom = findViewById(R.id.symptom);
//        img = findViewById(R.id.img);
//        comment = findViewById(R.id.comment);
//
//        Intent intent = getIntent();
//        int position = intent.getExtras().getInt("position");


    }
}