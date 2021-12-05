package com.example.aa;


import static android.R.layout.simple_spinner_item;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aa.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class StudyActivity extends Fragment implements View.OnClickListener {
    public StudyActivity() {
    }

    FirebaseAuth fbAuth;
    String col,colcol;

    RecyclerView mRecyclerView = null ;
    SimpleTextAdapter mAdapter = null ;
    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();
    private ListView listView;
    List fileList = new ArrayList<>();
    ArrayList<RecyclerItem> list = new ArrayList<>();
    ArrayAdapter adapter;
    static boolean calledAlready = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!calledAlready)
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true); // 다른 인스턴스보다 먼저 실행되어야 한다.
            calledAlready = true;
        }


        View v = inflater.inflate(R.layout.study, container, false);

        Button btn_write = v.findViewById(R.id.btn_writing);
        btn_write.setOnClickListener(this);
        //v.setId(View.generateViewId());
        String[][] str = new String[20][];
        str[0]= getResources().getStringArray(R.array.Col1);
        str[1]= getResources().getStringArray(R.array.Col2);
        str[2]= getResources().getStringArray(R.array.Col3);
        str[3]= getResources().getStringArray(R.array.Col4);
        str[4]= getResources().getStringArray(R.array.Col5);
        str[5]= getResources().getStringArray(R.array.Col6);
        str[6]= getResources().getStringArray(R.array.Col7);
        str[7]= getResources().getStringArray(R.array.Col8);
        str[8]= getResources().getStringArray(R.array.Col9);
        str[9]= getResources().getStringArray(R.array.Col10);
        str[10]= getResources().getStringArray(R.array.Col11);
        str[11]= getResources().getStringArray(R.array.Col12);
        str[12]= getResources().getStringArray(R.array.Col13);
        str[13]= getResources().getStringArray(R.array.Col14);
        str[14]= getResources().getStringArray(R.array.Col15);
        str[15]= getResources().getStringArray(R.array.Col16);
        str[16]= getResources().getStringArray(R.array.Col17);
        str[17]= getResources().getStringArray(R.array.Col18);
        str[18]= getResources().getStringArray(R.array.Col19);
        str[19]= getResources().getStringArray(R.array.Col20);

        Spinner spin1 = (Spinner) v.findViewById(R.id.spinner1);
        Spinner spin2 = (Spinner) v.findViewById(R.id.spinner2);
        ArrayAdapter<String> ad1 = new ArrayAdapter<String>(getActivity(), simple_spinner_item,getResources().getStringArray(R.array.College));
        //adapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_listitem, fileList);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("study");

        spin1.setAdapter(ad1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                col = (String)spin1.getSelectedItem();

                ArrayAdapter<String> ad2 = null;
                ad2 = new ArrayAdapter<String>(getActivity(), simple_spinner_item,str[i]);
                spin2.setAdapter(ad2);
                spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        colcol = (String)spin2.getSelectedItem();
                        fbAuth = FirebaseAuth.getInstance();
                        mList.clear();

                        databaseRef.child(col).child(colcol).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                                    String str = fileSnapshot.child("title").getValue(String.class);
                                    String str2 = fileSnapshot.child("content").getValue(String.class);
                                    String str3 = fileSnapshot.child("nickname").getValue(String.class);
                                    Log.i("TAG: value is ", str);
                                    RecyclerItem re = new RecyclerItem(str,str2);
                                    mList.add(re);
                                }
                                //adapter.notifyDataSetChanged();
                                // 리사이클러뷰에 LinearLayoutManager 객체 지정.
                                mRecyclerView = v.findViewById(R.id.recycler1) ;
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity())) ;
                                mAdapter = new SimpleTextAdapter(mList) ;
                                mRecyclerView.setAdapter(mAdapter) ;
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.w("TAG: ", "Failed to read value", databaseError.toException());   }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_writing:
                Intent intent = new Intent(getActivity(),WritingActivity.class);
                intent.putExtra("board", "study");
                intent.putExtra("col",col);
                intent.putExtra("colcol",colcol);
                startActivity(intent);
                break;
        }
    }
}