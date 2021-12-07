package com.example.aa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private ArrayList<Comment> arrayList = null;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nickname, comment, like;
        ViewHolder(View itemView) {
            super(itemView) ;
            nickname = itemView.findViewById(R.id.nickname);
            comment = itemView.findViewById(R.id.comment);
            like = itemView.findViewById(R.id.like);
        }
    }

    CommentAdapter() {
        arrayList = new ArrayList<>();
    }

    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(R.layout.comment_item, parent, false) ;
        CommentAdapter.ViewHolder v = new CommentAdapter.ViewHolder(view) ;

        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = arrayList.get(position) ;
        holder.comment.setText(comment.getComment()) ;
        holder.nickname.setText(comment.getNickname());
        holder.like.setText("좋아요" + comment.getLike());
    }

    @Override
    public int getItemCount() {
        return arrayList.size() ;
    }
    public void set(Comment comment){arrayList.add(comment);}
}
