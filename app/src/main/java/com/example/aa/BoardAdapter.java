package com.example.aa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.boardHolder> {
    private ArrayList<BoardItem> arrayList;
    BoardAdapter(ArrayList<BoardItem> list){
        arrayList = list;
    }
    @NonNull
    @Override
    public boardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        boardHolder holder = new boardHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull boardHolder holder, int position) {
        BoardItem item = arrayList.get(position);
        holder.title.setText(item.getTitle());
        holder.cont.setText(item.getCont());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class boardHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView cont;
        public boardHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.cont=itemView.findViewById(R.id.cont);
        }
    }
}
