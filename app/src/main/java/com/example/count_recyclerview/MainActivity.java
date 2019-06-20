package com.example.count_recyclerview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context mContext ;
    ArrayList<String> mName =  new ArrayList<>();
    ArrayList<String> mNum =new ArrayList<>();
    ArrayList<String> mSum = new ArrayList<>();
    ArrayList<String> mHistory = new ArrayList<>();
    ArrayList<String> mChange = new ArrayList<>();
    RecyclerViewAdapter adapter;
    private View.OnClickListener btn_click  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = txt.getText().toString();
            if(str != ""){
                adapter.addData(mName.size(),str);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitialComponent();
        InitialDataSet();
    }

    private void InitialDataSet() {
    mName.add("紗布 4*4");
    mName.add("針組");

    mNum.add("1");
    mNum.add("2");

    mSum.add("0");
    mSum.add("0");

    mHistory.add("");
    mHistory.add("");
    mChange.add("10");
        mChange.add("1");
    }

    private void InitialComponent() {
    recyclerView= findViewById(R.id.RecyclerView);
    adapter = new RecyclerViewAdapter(this,mName,mNum,mSum,mHistory,mChange);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    btn= findViewById(R.id.btn);
    btn.setOnClickListener(btn_click);
    txt=findViewById(R.id.txtInput);
    }


    RecyclerView recyclerView;
    Button btn;
    EditText txt;
}
