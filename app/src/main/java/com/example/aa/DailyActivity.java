package com.example.aa;

import android.content.Intent;
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

public class DailyActivity extends Fragment implements View.OnClickListener {
    private ArrayList<Writing> arrayList = new ArrayList<Writing>();
    private RecyclerView recyclerView;
    WritingAdapter writingAdapter;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    public DailyActivity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.daily, container, false);
        recyclerView = v.findViewById(R.id.recy);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(writingAdapter);
        Button btn_write = v.findViewById(R.id.btn_writing);
        btn_write.setOnClickListener(this);
        writingAdapter = new WritingAdapter();
        databaseReference=firebaseDatabase.getReference("daily");
        arrayList.clear();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datasnapshot : snapshot.getChildren()){
                    Writing item = datasnapshot.getValue(Writing.class);
                    arrayList.add(item);
                }
                setDaily();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_writing:
                Intent intent = new Intent(getActivity(), WritingActivity.class);
                intent.putExtra("board","daily");
                startActivity(intent);
                break;
        }
    }
    public void setDaily(){
        for(int i=0; i<arrayList.size(); i++){
            writingAdapter.set(arrayList.get(i));
        }
        recyclerView.setAdapter(writingAdapter);
    }
}
