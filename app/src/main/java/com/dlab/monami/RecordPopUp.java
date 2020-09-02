package com.dlab.monami;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dlab.monami.R;
//import com.github.chrisbanes.photoview.PhotoView;

public class RecordPopUp extends Activity {

    private TextView tv_time;
    private TextView tv_writer;
    private TextView tv_title;
    private TextView tv_symptom;
    private ImageView iv_img;
    private TextView tv_comment;

    String time;
    String writer;
    String title;
    String symptom;
    String imgUrl;
    String comment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_record_pop_up);

        tv_time = findViewById(R.id.bar_time);
        tv_writer = findViewById(R.id.bar_writer);
        tv_title = findViewById(R.id.title);
        tv_symptom = findViewById(R.id.symptom);
        iv_img = findViewById(R.id.img);
        tv_comment = findViewById(R.id.comment);

        Intent intent = getIntent();

        time = intent.getExtras().getString("time");
        writer = intent.getExtras().getString("writer");
        title = intent.getExtras().getString("title");
        symptom = intent.getExtras().getString("symptom");
        imgUrl = intent.getExtras().getString("imgurl");
        comment = intent.getExtras().getString("comment");

        Log.d("popup", time);
        Log.d("popup", writer);

        Glide.with(this)
                //.load(imageUrls.get(i).getImageUrl()) // 웹 이미지 로드
                .load(imgUrl) // imageUrl
                .listener( new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .thumbnail(0.1f)
                .error(R.drawable.sample_img)   // 없을 때 뭘로 대체?
                .into(iv_img);  // 어디에 뿌릴 건지

        tv_time.setText(time);
        tv_writer.setText(writer);
        tv_title.setText(title);
        tv_symptom.setText(symptom);
        tv_comment.setText(comment);

    }
}