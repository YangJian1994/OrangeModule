package com.example.orangemodule.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.orangemodule.R;
import com.example.orangemodule.dialog.PwdDialog;

public class UDiskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udisk);

    }

    //启动U盘界面
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, UDiskActivity.class);
        context.startActivity(intent);
    }
}
