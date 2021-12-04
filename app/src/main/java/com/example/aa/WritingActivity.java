package com.example.aa;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WritingActivity extends AppCompatActivity {
    private Button btn_write;
    private EditText et_title, et_content;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private Uri uri;
    private String nickname;

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
        String cuid = firebaseUser.getUid();
        getNickname(cuid);
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String content = et_content.getText().toString();
                Writing writing = new Writing(title, content, nickname , board, firebaseUser.getEmail());
                if (board == "study"){
                    String col = intent.getStringExtra("col");
                    String colcol = intent.getStringExtra("colcol");
                    databaseReference.child(board).child(col).child(colcol).push().setValue(writing).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(WritingActivity.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(WritingActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                else {
                    databaseReference.child(board).push().setValue(writing).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(WritingActivity.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(WritingActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                }
                focusView.clearFocus();
            }

        }
        return super.dispatchTouchEvent(ev);
    }

    public void getNickname(String cuid) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child(cuid).child("nickname");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nickname = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
