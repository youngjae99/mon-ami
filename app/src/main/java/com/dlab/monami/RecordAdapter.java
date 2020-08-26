package com.dlab.monami;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecodeViewHolder>{

    String TAG = "Recode Adapter";
    ArrayList<RecordItem> items = new ArrayList<RecordItem>();

    // ViewHolder : 각 view를 보관하는 holder 객체

    public static class RecodeViewHolder extends RecyclerView.ViewHolder {
        protected TextView writer;      // 작성자
        protected TextView time;      // 기록 시간

        public RecodeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.writer = itemView.findViewById(R.id.writer);
            this.time = itemView.findViewById(R.id.time);

        }
    }

    public RecordAdapter(ArrayList<RecordItem> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public RecordAdapter.RecodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item, parent, false);
        RecordAdapter.RecodeViewHolder recodeViewHolder = new RecordAdapter.RecodeViewHolder(view);
        return recodeViewHolder;
    }

    // ViewHolder의 내용을 변경
    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.RecodeViewHolder holder, int position) {

        holder.time.setText(items.get(position).getTime());
        holder.writer.setText(items.get(position).getWriter());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}