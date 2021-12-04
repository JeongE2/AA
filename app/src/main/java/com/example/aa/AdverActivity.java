package com.example.aa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AdverActivity extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ArrayList<Writing> arrayList;
    private WritingAdapter writingAdapter;
    private Button btn_write;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    @SuppressLint("NotifyDataSetChanged")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.adver, container, false);
        writingAdapter = new WritingAdapter();
        arrayList = new ArrayList<>();
        recyclerView = v.findViewById(R.id.recy);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(writingAdapter);
        btn_write = v.findViewById(R.id.btn_writing);
        btn_write.setOnClickListener(this);
        databaseReference = firebaseDatabase.getReference("adver");
        arrayList.clear();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datasnapshot : snapshot.getChildren()){
                    Writing item = datasnapshot.getValue(Writing.class);
                    arrayList.add(item);
                }
                setAdver();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_writing:
                Intent intent = new Intent(getActivity(), WritingActivity.class);
                intent.putExtra("board","adver");
                startActivity(intent);
                break;
        }

        };

    public void setAdver(){
        for(int i=0; i<arrayList.size(); i++){
            writingAdapter.set(arrayList.get(i));
        }
        recyclerView.setAdapter(writingAdapter);
    }
}
