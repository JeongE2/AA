package com.example.aa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button study, out, daily, adver, mypage,store;
    ImageButton home;
    TextView weather,cel;
    ImageView sun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        study = findViewById(R.id.study);
        out = findViewById(R.id.out);
        daily = findViewById(R.id.daily);
        adver = findViewById(R.id.adver);
        mypage = findViewById(R.id.mypage);
        weather = findViewById(R.id.weather);
        home = findViewById(R.id.home);
        sun = findViewById(R.id.sun);
        sun.setImageResource(R.drawable.sunsn);
        store = findViewById(R.id.store);
        cel = findViewById(R.id.cel);

        study.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                weather.setVisibility(View.INVISIBLE);
                store.setVisibility(View.INVISIBLE);
                sun.setVisibility(View.INVISIBLE);
                cel.setVisibility(View.INVISIBLE);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                StudyActivity study = new StudyActivity();
                transaction.replace(R.id.frame, study);
                transaction.commit();
            }
        });

        out.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                weather.setVisibility(View.INVISIBLE);
                store.setVisibility(View.INVISIBLE);
                sun.setVisibility(View.INVISIBLE);
                cel.setVisibility(View.INVISIBLE);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
               OutActivity out = new OutActivity();
                transaction.replace(R.id.frame, out);
                transaction.commit();
            }
        });

        daily.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                weather.setVisibility(View.INVISIBLE);
                store.setVisibility(View.INVISIBLE);
                sun.setVisibility(View.INVISIBLE);
                cel.setVisibility(View.INVISIBLE);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                DailyActivity daily = new DailyActivity();
                transaction.replace(R.id.frame, daily);
                transaction.commit();
            }
        });

        adver.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                weather.setVisibility(View.INVISIBLE);
                store.setVisibility(View.INVISIBLE);
                sun.setVisibility(View.INVISIBLE);
                cel.setVisibility(View.INVISIBLE);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                AdverActivity adver = new AdverActivity();
                transaction.replace(R.id.frame, adver);
                transaction.commit();
            }
        });

        mypage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                weather.setVisibility(View.INVISIBLE);
                store.setVisibility(View.INVISIBLE);
                sun.setVisibility(View.INVISIBLE);
                cel.setVisibility(View.INVISIBLE);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                MypageActivity mypage = new MypageActivity();
                transaction.replace(R.id.frame, mypage);
                transaction.commit();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weather.setVisibility(View.INVISIBLE);
                store.setVisibility(View.INVISIBLE);
                sun.setVisibility(View.INVISIBLE);
                cel.setVisibility(View.INVISIBLE);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                StoreActivity store = new StoreActivity();
                transaction.replace(R.id.frame, store);
                transaction.commit();
            }
        });


    }
}