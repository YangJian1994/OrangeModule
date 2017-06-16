package com.example.orangemodule.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.orangemodule.R;

public class WristbandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wristband);
    }

    //启动手环界面
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, WristbandActivity.class);
        context.startActivity(intent);
    }
}
