package com.dlab.monami;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder>{

    String TAG = "Recode Adapter";
    ArrayList<RecordItem> items = new ArrayList<RecordItem>();

    // ViewHolder : 각 view를 보관하는 holder 객체

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        protected TextView writer;      // 작성자
        protected TextView time;      // 기록 시간

        public RecordViewHolder(@NonNull View itemView) {
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
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item, parent, false);
        RecordViewHolder recordViewHolder = new RecordViewHolder(view);
        return recordViewHolder;
    }

    // ViewHolder의 내용을 변경
    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {

        holder.time.setText(items.get(position).getTime());
        holder.writer.setText(items.get(position).getWriter());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}