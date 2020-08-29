package com.dlab.monami;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Fragment1 extends Fragment {
    public Fragment1() {
        // Required empty public constructor

    }

    private DatabaseReference mPostReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<RecordItem> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private ImageButton addBtn;

    private View view;                                    // 갤러리 이미지 탐색

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment1, container, false);
        Log.d("Fragment1","onCreateView");
        addBtn = view.findViewById(R.id.addBtn);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // User 객체를 담을 어레이 리스트 (어댑터쪽으로)

        mPostReference = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference().child("patient_list"); // DB 테이블 연결

        final Query query = databaseReference.orderByChild("time");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) {
                    getFirebaseDatabase();
                } else {
                    //user not found
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("Fragment1", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewRecord.class);
                getContext().startActivity(intent);
            }
        });

        adapter = new RecordAdapter(arrayList, getContext());
        adapter.notifyDataSetChanged();     // 내림차순 정렬
        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어댑터 연결

        return view;
    }

    public void getFirebaseDatabase(){
        final ValueEventListener postListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("onDataChange","Data is Updated");
                arrayList.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    String key=postSnapshot.getKey();
                    FirebasePost get = postSnapshot.getValue(FirebasePost.class);
                    String[] info={get.time, get.writer, get.title, get.symptom, get.img, get.comment};
                    RecordItem result= new RecordItem(info[0],info[1],info[2],info[3],info[4],info[5]); //수정 !!!

                    //list.add(result);
                    if(info[4]!=null){  //행 사진파일이 있을 때 실
                        //RecordItem recordItem = new RecordItem();

                        result.setTime(info[0]);
                        result.setWriter(info[1]);
                        result.setTitle(info[2]);
                        result.setSymptom(info[3]);
                        result.setImgUrl(info[4]);
                        result.setComment(info[5]);
                        arrayList.add(result);
                    }


                    Log.d("getFirebaseDatabase","key: "+key);
                    Log.d("getFirebaseDatabase","info: "+info[0]+" "+info[1]);
                    Log.d("ListSize",String.valueOf(arrayList.size()));

                }
                adapter.notifyDataSetChanged();

                Collections.reverse(arrayList); // 내림차순 정렬
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mPostReference.child("patient_list").child("hyunwoo").addValueEventListener(postListener);
    }




}
