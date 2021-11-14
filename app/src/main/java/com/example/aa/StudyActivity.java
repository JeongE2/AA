package com.example.aa;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.aa.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class StudyActivity extends Fragment {
    public StudyActivity(){
    }
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference myRef = database.getReference();
    Button btn;
    RecyclerView listview1;
    EditText edit1, edit2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.study, container, false);
        btn = (Button) v.findViewById(R.id.btn);
        edit1 = v.findViewById(R.id.edit1);
        edit2 = v.findViewById(R.id.edit2);
        ListView listview = (ListView) v.findViewById(R.id.listview1);
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,android.R.id.text1);
        listview.setAdapter(adapter);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("Study").addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Board board = snapshot.getValue(Board.class);
                adapter.add(board.getTitle());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                Board board = new Board(edit1.getText().toString(),edit2.getText().toString());
                database.child("Study").push().setValue(board);
            }
        });


        return v;
    }
    //public void clickReg(View v){
      //  DatabaseReference database = FirebaseDatabase.getInstance().getReference("message");
       // database.setValue("Hello");
        //Board board = new Board(edit1.getText().toString(),edit2.getText().toString());
        //database.child("study").push().setValue(board);
    //}
}

class Board{
    String title;
    String content;

    Board(){}

    Board(String title,String content){
        this.title = title;
        this.content = content;
    }
    public String getTitle(){return title;}
    public String getContent(){ return content;}
}
