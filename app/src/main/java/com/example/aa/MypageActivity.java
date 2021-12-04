package com.example.aa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MypageActivity extends Fragment implements View.OnClickListener {
    private View view;
    private TextView nickname;
    private Button myw, myc, logout;
    public MypageActivity(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mypage, container, false);
        nickname = view.findViewById(R.id.usernickname);
        myw = view.findViewById(R.id.mywriting);
        myc = view.findViewById(R.id.mycom);
        logout = view.findViewById(R.id.logout);
        myw.setOnClickListener(this);
        myc.setOnClickListener(this);
        logout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.mywriting:
                intent = new Intent(getActivity(),MyWriting.class);
                startActivity(intent);
                break;
            case R.id.mycom:
                intent = new Intent(getActivity(),MyComment.class);
                startActivity(intent);
                break;
            case R.id.logout:
                new AlertDialog.Builder(getActivity()).setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?").setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
        }
    }
}
