package com.dlab.monami;

import android.content.Intent;
import android.database.Cursor;
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
/*
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

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

    public Integer type;
    public String title;
    public String symptom;
    public String img;
    public String comment;

    private View view;                                    // 갤러리 이미지 탐색

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Fragment1","onCreateView");
        view = inflater.inflate(R.layout.fragment1, container, false);
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
//                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
//                arrayList.clear(); // 기존 배열리스트가 존재하지않게 초기화
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
//                    RecordItem recordItem = snapshot.getValue(RecordItem.class); // 만들어뒀던 User 객체에 데이터를 담는다.
//                    arrayList.add(recordItem); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
//                }
//                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침해야 반영이 됨
                if (dataSnapshot.getChildrenCount() > 0) {
                    getFirebaseDatabase();

                } else {
                    //user not found
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("Fraglike", String.valueOf(databaseError.toException())); // 에러문 출력
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
                    FirebasePost get=postSnapshot.getValue(FirebasePost.class);
                    String[] info={get.time,get.writer};
                    RecordItem result= new RecordItem(info[0],info[1]); //수정 !!!

                    title = get.title;
                    symptom = get.symptom;
                    img = get.img;
                    comment = get.comment;

                    arrayList.add(result);
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
