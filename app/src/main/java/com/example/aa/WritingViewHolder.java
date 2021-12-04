package com.example.aa;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WritingViewHolder extends RecyclerView.ViewHolder{
TextView title, content, nickname;
    public WritingViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        content = itemView.findViewById(R.id.content);
        nickname = itemView.findViewById(R.id.nickname);
    }
}
