package com.example.aa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WritingAdapter extends RecyclerView.Adapter<WritingViewHolder> {
    private ArrayList<Writing> writingList = null;
    WritingAdapter(){
        writingList=new ArrayList<>();
    }
    @NonNull
    @Override
    public WritingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.writing_item,parent,false);
        WritingViewHolder viewHolder = new WritingViewHolder(context,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WritingViewHolder holder, int position) {
        Writing item = writingList.get(position);
        String title = item.getTitle();
        String content = item.getContent();
        String nickname = item.getNickname();
        String board = item.getBoard();
        holder.title.setText(title);
        holder.content.setText(content);
        holder.nickname.setText(nickname);
    }

    @Override
    public int getItemCount() {
        return writingList.size();
    }
    public void set(Writing writing){
        writingList.add(writing);
    }
}

