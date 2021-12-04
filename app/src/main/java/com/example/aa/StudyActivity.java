package com.example.aa;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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


public class StudyActivity extends Fragment implements View.OnClickListener {
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
        View v = inflater.inflate(R.layout.daily, container, false);
        Button btn_write = v.findViewById(R.id.btn_writing);
        btn_write.setOnClickListener(this);
        return v;

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_writing:
                Intent intent = new Intent(getActivity(), WritingActivity.class);
                intent.putExtra("board","study");
                startActivity(intent);
                break;
        }
    }
    //public void clickReg(View v){
      //  DatabaseReference database = FirebaseDatabase.getInstance().getReference("message");
       // database.setValue("Hello");
        //Board board = new Board(edit1.getText().toString(),edit2.getText().toString());
        //database.child("study").push().setValue(board);
    //}
}

