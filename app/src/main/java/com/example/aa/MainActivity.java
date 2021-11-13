package com.example.aa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button study, out, daily, adver, mypage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        study = findViewById(R.id.study);
        out = findViewById(R.id.out);
        daily = findViewById(R.id.daily);
        adver = findViewById(R.id.adver);
        mypage = findViewById(R.id.mypage);

        study.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                StudyActivity study = new StudyActivity();
                transaction.replace(R.id.frame, study);
                transaction.commit();
            }
        });

        out.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
               OutActivity out = new OutActivity();
                transaction.replace(R.id.frame, out);
                transaction.commit();
            }
        });

        daily.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                DailyActivity daily = new DailyActivity();
                transaction.replace(R.id.frame, daily);
                transaction.commit();
            }
        });

        adver.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                AdverActivity adver = new AdverActivity();
                transaction.replace(R.id.frame, adver);
                transaction.commit();
            }
        });

        mypage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                MypageActivity mypage = new MypageActivity();
                transaction.replace(R.id.frame, mypage);
                transaction.commit();
            }
        });




    }
}