package com.example.aopdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aopdemo.aspect.annotation.SelectAnnotation;
import com.example.aopdemo.aspect.annotation.SingleClick;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private TextView mSingle, mNormal;

    private int mNornalSum = 0;
    private int mSingleSum = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.button);
        mNormal = findViewById(R.id.normal);
        mSingle = findViewById(R.id.single);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                normal();
                single();
            }
        });


        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharkItOff("name", 1);
            }


        });

    }

    @SelectAnnotation(name = "摇一摇")
    public void sharkItOff(String str,int a) {
    }

    private void normal(){
        mNormal.setText(String.format("点击次数:%s次", mNornalSum ++));
    }

    @SingleClick
    private void single(){
        mSingle.setText(String.format("防止多次点击:%s次", mSingleSum ++));
    }


}




