package com.dlab.monami;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dlab.monami.R;

import java.util.ArrayList;

public class RecodeAdapter extends RecyclerView.Adapter<RecodeAdapter.RecodeViewHolder>{

    String TAG = "Recode Adapter";
    ArrayList<RecodeItem> items = new ArrayList<RecodeItem>();

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

    public RecodeAdapter(ArrayList<RecodeItem> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public RecodeAdapter.RecodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recode_item, parent, false);
        RecodeAdapter.RecodeViewHolder recodeViewHolder = new RecodeAdapter.RecodeViewHolder(view);
        return recodeViewHolder;
    }

    // ViewHolder의 내용을 변경
    @Override
    public void onBindViewHolder(@NonNull RecodeAdapter.RecodeViewHolder holder, int position) {

        holder.time.setText(items.get(position).getTime());
        holder.writer.setText(items.get(position).getWriter());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}