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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class NewRecord extends AppCompatActivity {
    //Firebase ------------------------------
    private DatabaseReference mPostReference;
    static String title="", symptom="", img="", comment="", recordtime="";

    //xml layout ----------------------------
    ImageButton backButton, datePickButton;
    ImageButton imgUploadButton;
    EditText titleET, commentET;
    AutoCompleteTextView symptomET;

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
        titleET = findViewById(R.id.input_title);
        symptomET = findViewById(R.id.input_symptom);
        commentET = findViewById(R.id.input_comment);

        mPostReference= FirebaseDatabase.getInstance().getReference();
        mStorageRef= FirebaseStorage.getInstance().getReference("Images");

//        Spinner spinner = (Spinner) findViewById(R.id.symptoms_spinner); // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.my_array, android.R.layout.simple_spinner_item); // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Apply the adapter to the spinner
//        spinner.setAdapter(adapter);

        // BackButton -------------------------------------------
        backButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        // Save Button Click ------------------------------------
        TextView saveBtn = (TextView)findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("saveButton","pressed");

                title=titleET.getText().toString();
                symptom=symptomET.getText().toString();
                comment=commentET.getText().toString();

                Date currentTime = Calendar.getInstance().getTime();
                String date_text = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(currentTime);
                Log.d("Saved time", date_text);

                if(title.length()==0){
                    Toast.makeText(NewRecord.this,"Please fill in required values.", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("patient_list");
                    Query query=ref.orderByChild("name").equalTo("david"); // ========================= TEMP NAME : david

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getChildrenCount()>0){ // Username found
                                Toast.makeText(NewRecord.this,"Restaurant already exists.", Toast.LENGTH_SHORT).show();
                            }
                            else{ //Username not found
                                Toast.makeText(NewRecord.this,"add success", Toast.LENGTH_SHORT).show();

                                postFirebaseDatabase(true);

                                //Image upload
                                if(check){
                                    final StorageReference riverseRef=mStorageRef.child(currentImageUri.getLastPathSegment());
                                    final UploadTask uploadTask=riverseRef.putFile(currentImageUri);
                                    Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                        @Override
                                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                            if(!task.isSuccessful()){
                                                //throw.task.getException();
                                            }
                                            return riverseRef.getDownloadUrl();
                                        }
                                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Uri> task) {
                                            if(task.isSuccessful()){
                                                Uri downloadUri = task.getResult();
                                                Log.d("imageLog", String.valueOf(downloadUri));
                                                /*
                                                img = String.valueOf(downloadUri);
                                                setImgUrl(String.valueOf(downloadUri));
                                                postFirebaseDatabase(true, img);*/
                                            }else{

                                            }
                                        }
                                    });
                                }
                                Log.d("imageLog","this img "+img);
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(NewRecord.this,"Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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

        ArrayAdapter symptom_adapter = ArrayAdapter.createFromResource(this, R.array.my_array, android.R.layout.simple_spinner_item);

        final AutoCompleteTextView autoTextView = (AutoCompleteTextView) findViewById(R.id.input_symptom);
        autoTextView.setAdapter(symptom_adapter);
        autoTextView.setThreshold(1);
        autoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoTextView.showDropDown();
            }
        });
        ImageButton downbtn=(ImageButton)findViewById(R.id.search_btn);
        downbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoTextView.showDropDown();
            }
        });

    }

    public void postFirebaseDatabase(boolean add){
        Map<String,Object> childUpdates=new HashMap<>();
        Map<String,Object> postValues=null;
        if(add){
            FirebasePost post=new FirebasePost(title, symptom, comment);
            postValues=post.toMap();
        }
        Date currentTime = Calendar.getInstance().getTime();
        String date_text = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(currentTime);
        childUpdates.put("/patient_list/"+date_text,postValues);
        mPostReference.updateChildren(childUpdates);
        //clearET();
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
