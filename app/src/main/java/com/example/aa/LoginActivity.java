package com.example.aa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button signup, login;
    EditText email, passwd;
    FirebaseAuth firebaseAuth;
    private CheckBox setid;
    private SharedPreferences setting;
    private SharedPreferences.Editor editor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        signup = findViewById(R.id.btn_signup);
        login = findViewById(R.id.btn_login);
        email = findViewById(R.id.et_email);
        passwd = findViewById(R.id.et_passwd);
        setid = findViewById(R.id.setid);
        firebaseAuth = FirebaseAuth.getInstance();

        setting = getSharedPreferences("setting", 0);
        editor = setting.edit();
        if(setting.getBoolean("setid", false)){
            email.setText(setting.getString("setID", ""));
            setid.setChecked(true);
        }
        setid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setid.isChecked()){
                    String id = email.getText().toString();
                    editor.putString("setID", id);
                    editor.putBoolean("setid", true);
                    editor.commit();
                }
                else{
                    editor.clear();
                    editor.commit();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uemail = email.getText().toString().trim();
                String upasswd = passwd.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(uemail,upasswd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "로그인 완료", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "이메일이나 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
