package com.dlab.monami;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment1 extends Fragment {
    public Fragment1() {
        // Required empty public constructor

    }
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

    String TAG = "PJ2 PhoneFragment";

    RecyclerView recyclerView;                              // 갤러리 출력될 recyclerView
    RecyclerView.LayoutManager layoutManager;

    ImageButton addBtn;

    Cursor cursor;                                          // 갤러리 이미지 탐색
    RecordAdapter phoneAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Fragment1","onCreateView");
        final View v = inflater.inflate(R.layout.fragment1, container, false);

        recyclerView = v.findViewById(R.id.recyclerView);
        addBtn = v.findViewById(R.id.add);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewRecord.class);
                getContext().startActivity(intent);
            }
        });

        return v;
    }


}
