package com.example.aa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WritingActivity extends AppCompatActivity {
    private Button btn_write;
    private EditText et_title, et_content;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writing);
        Intent intent = getIntent();
        String board = intent.getExtras().getString("board");
        btn_write = findViewById(R.id.btn_writing);
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_writing);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseUser = firebaseAuth.getCurrentUser();
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String content = et_content.getText().toString();
                Writing writing = new Writing(title, content,"username",board,firebaseUser.getEmail());
                databaseReference.child("writing").push().setValue(writing).addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(WritingActivity.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(WritingActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
