package com.example.orangemodule.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.orangemodule.R;

public class BluetoothActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
    }

    //启动蓝牙耳机界面
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, BluetoothActivity.class);
        context.startActivity(intent);
    }
}
