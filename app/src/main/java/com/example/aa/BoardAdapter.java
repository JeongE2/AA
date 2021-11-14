package com.example.aa;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.boardHolder> {
    private ArrayList<boardItem> arrayList;
    BoardAdapter(ArrayList<boardItem> list){
        arrayList = list;
    }
    @NonNull
    @Override
    public boardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull boardHolder holder, int position) {
        boardItem item = arrayList.get(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class boardHolder extends RecyclerView.ViewHolder {
        public boardHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
