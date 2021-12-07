package com.example.aa;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class ReadActivity extends AppCompatActivity {
    ArrayList<Comment> arrayList;
    private RecyclerView recyclerView;
    CommentAdapter commentAdapter;
    String title, content, nickname, board;
    TextView t_title, t_content, t_nickname;
    EditText comment;
    Button reg;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String com, nick, uid, path, wuid = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read);
        Intent intent =  getIntent();
        t_title = findViewById(R.id.title);
        t_content = findViewById(R.id.content);
        t_nickname = findViewById(R.id.nickname);
        comment = findViewById(R.id.comment);
        reg = findViewById(R.id.btn_comment);
        recyclerView = findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (this, RecyclerView.VERTICAL, false));
        arrayList = new ArrayList<>();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        nickname = intent.getStringExtra("nickname");
        board = intent.getStringExtra("board");
        wuid = intent.getStringExtra("uid");
        path = wuid+title+content;
        t_title.setText(title);
        t_content.setText(content);
        t_nickname.setText(nickname);
        databaseReference = firebaseDatabase.getReference(board);
        databaseReference2 = firebaseDatabase.getReference(board);
        //commentAdapter = new CommentAdapter();
        uid = user.getUid();
        getnickname();
        comments();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com = comment.getText().toString();
                if(!com.equals("")){
                    Comment comments = new Comment(com, 0,false, nick);
                    databaseReference.child(path).child("comment").child(com).setValue(comments);
                    Toast.makeText(ReadActivity.this, "댓글을 등록했습니다.", Toast.LENGTH_SHORT).show();
                    comment.getText().clear();
                    comments();
                }
            }
        });

    }

    public void getnickname(){
        FirebaseDatabase.getInstance().getReference("User").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nick = snapshot.getValue(User.class).getNickname();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setcomment(){
        commentAdapter = new CommentAdapter();
        for(int i=0; i<arrayList.size(); i++){
            commentAdapter.set(arrayList.get(i));
        }
        recyclerView.setAdapter(commentAdapter);
    }

    public void comments(){
        arrayList.clear();
        FirebaseDatabase.getInstance().getReference(board).child(path).child("comment").addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                Comment comment = dataSnapshot.getValue(Comment.class);
                arrayList.add(comment);
            }
            setcomment();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });}

}
