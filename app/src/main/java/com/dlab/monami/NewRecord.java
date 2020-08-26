package com.dlab.monami;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class NewRecord extends AppCompatActivity {
    //Firebase ------------------------------
    private DatabaseReference mPostReference;
    static String title="", symptom="", img="", comment="", recordtime="";

    //xml layout ----------------------------
    ImageButton backButton, datePickButton;
    ImageButton imgUploadButton;

    //image ---------------------------------
    private static final int PICK_IMAGE=777;
    private StorageReference mStorageRef;
    Uri currentImageUri;
    boolean check;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newrecordscreen);

        backButton = (ImageButton) findViewById(R.id.backbtn);
        datePickButton = (ImageButton) findViewById(R.id.downbtn);
        imgUploadButton = (ImageButton) findViewById(R.id.imguploadbtn);

        mPostReference= FirebaseDatabase.getInstance().getReference();
        mStorageRef= FirebaseStorage.getInstance().getReference("Images");

//        Spinner spinner = (Spinner) findViewById(R.id.symptoms_spinner); // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.my_array, android.R.layout.simple_spinner_item); // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Apply the adapter to the spinner
//        spinner.setAdapter(adapter);

        backButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView saveBtn = (TextView)findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("saveButton","pressed");
            }
        });

        ImageButton image=(ImageButton)findViewById(R.id.imguploadbtn);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery,PICK_IMAGE);
            }
        });

        String[] countryNames = {"Korea", "Kenya", "India", "China", "Australia", "New Zealand", "England", "Pakistan"};
        ArrayAdapter symptom_adapter = ArrayAdapter.createFromResource(this, R.array.my_array, android.R.layout.simple_spinner_item);

        AutoCompleteTextView autoTextView = (AutoCompleteTextView) findViewById(R.id.input_symptom);
        autoTextView.setAdapter(symptom_adapter);
        autoTextView.setThreshold(1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE){
            ImageView img = (ImageView) findViewById(R.id.imguploadbtn);
            currentImageUri = data.getData();
            check=true;
            img.setImageURI(currentImageUri);
        }
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(R.anim.slide_down,R.anim.slide_down);
    }
}
