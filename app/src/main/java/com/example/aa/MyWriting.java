package com.example.aa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyWriting extends AppCompatActivity {
    RecyclerView recyclerView;
    WritingAdapter writingAdapter;
    ArrayList<Writing> arrayList;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mywriting);
        recyclerView = findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (this, RecyclerView.VERTICAL, false)) ;
        writingAdapter = new WritingAdapter();
        arrayList = new ArrayList<>();
        databaseReference = firebaseDatabase.getReference("adver");
        setAdver();
        databaseReference = firebaseDatabase.getReference("daily");
        setDaily();
        databaseReference = firebaseDatabase.getReference("out");
        setOut();
    }

    public void setList(){
        for(int i=0; i<arrayList.size(); i++){
            writingAdapter.set(arrayList.get(i));
        }
        recyclerView.setAdapter(writingAdapter);
    }

    public void setAdver(){
        arrayList.clear();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datasnapshot : snapshot.getChildren()){
                    Writing item = datasnapshot.getValue(Writing.class);
                    arrayList.add(item);
                }
                setList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setDaily(){
        arrayList.clear();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datasnapshot : snapshot.getChildren()){
                    Writing item = datasnapshot.getValue(Writing.class);
                    arrayList.add(item);
                }
                setList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setOut(){
        arrayList.clear();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datasnapshot : snapshot.getChildren()){
                    Writing item = datasnapshot.getValue(Writing.class);
                    arrayList.add(item);
                }
                setList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
