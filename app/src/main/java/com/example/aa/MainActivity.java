package com.example.aa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    Button store;
    ImageButton home;
    TextView weather,cel;
    ImageView sun;
    BottomNavigationView bottomNavigationView;
    StudyActivity studyActivity;
    OutActivity outActivity;
    DailyActivity dailyActivity;
    AdverActivity adverActivity;
    MypageActivity mypageActivity;
    FragmentTransaction ft;
    FragmentManager fm;
    FrameLayout frame2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weather = findViewById(R.id.weather);
        home = findViewById(R.id.home);
        sun = findViewById(R.id.sun);
        sun.setImageResource(R.drawable.sunsn);
        store = findViewById(R.id.store);
        cel = findViewById(R.id.cel);
        sun.setVisibility(View.VISIBLE);
        weather.setVisibility(View.VISIBLE);
        cel.setVisibility(View.VISIBLE);
        store.setVisibility(View.VISIBLE);
        studyActivity = new StudyActivity();
        dailyActivity = new DailyActivity();
        outActivity = new OutActivity();
        adverActivity = new AdverActivity();
        mypageActivity = new MypageActivity();


        bottomNavigationView = findViewById(R.id.navi);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                switch (item.getItemId()){
                    case R.id.study:
                        sun.setVisibility(View.GONE);
                        weather.setVisibility(View.GONE);
                        cel.setVisibility(View.GONE);
                        store.setVisibility(View.GONE);
                        ft.replace(R.id.frame, studyActivity);
                        ft.commit();
                        break;
                    case R.id.out:
                        sun.setVisibility(View.GONE);
                        weather.setVisibility(View.GONE);
                        cel.setVisibility(View.GONE);
                        store.setVisibility(View.GONE);
                        ft.replace(R.id.frame,outActivity);
                        ft.commit();
                        break;
                    case R.id.daily:
                        sun.setVisibility(View.GONE);
                        weather.setVisibility(View.GONE);
                        cel.setVisibility(View.GONE);
                        store.setVisibility(View.GONE);
                        ft.replace(R.id.frame,dailyActivity);
                        ft.commit();
                        break;
                    case R.id.adver:
                        sun.setVisibility(View.GONE);
                        weather.setVisibility(View.GONE);
                        cel.setVisibility(View.GONE);
                        store.setVisibility(View.GONE);
                        ft.replace(R.id.frame,adverActivity);
                        ft.commit();
                        break;
                    case R.id.mypage:
                        sun.setVisibility(View.GONE);
                        weather.setVisibility(View.GONE);
                        cel.setVisibility(View.GONE);
                        store.setVisibility(View.GONE);
                        ft.replace(R.id.frame,mypageActivity);
                        ft.commit();
                        break;
                }
                return true;
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