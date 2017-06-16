package com.example.orangemodule.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.orangemodule.R;
import com.example.orangemodule.bean.AirOnce;
import com.example.orangemodule.bean.Message;
import com.example.orangemodule.communication.UsbCommunication;
import com.example.orangemodule.utils.MessageUtils;
import com.example.orangemodule.widget.CircleProgressBar;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AirActivity extends AppCompatActivity {

    private static final String TAG = AirActivity.class.getSimpleName();
    @Bind(R.id.AQI_Circle)
    CircleProgressBar AQICircle;
    @Bind(R.id.PM_Circle)
    CircleProgressBar PMCircle;
    @Bind(R.id.tv_airDetail)
    TextView tvAirDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air);
        ButterKnife.bind(this);

        initCircle();
        initView();
        initListener();
    }

    //初始化圆圈数据
    private void initCircle() {
        AQICircle.setMax(500);
        PMCircle.setMax(500);

        AQICircle.setTitle("AQI");
        PMCircle.setTitle("PM2.5");

        AQICircle.setTextColor(Color.GREEN);
        PMCircle.setTextColor(Color.GREEN);
    }

    //初始化
    private void initView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                UsbCommunication communication = UsbCommunication.getInstance(AirActivity.this);
                communication.sendMessage(Message.sendAirBytes());
                String jsonData = MessageUtils.printData(communication.receiveMessage());
                Gson gson = new Gson();
                AirOnce airOnce = gson.fromJson(jsonData, AirOnce.class);
                Log.e(TAG, jsonData);
                AQICircle.setProgress(airOnce.getAQI());
                PMCircle.setProgress(airOnce.getPM25());
            }
        });
    }

    private void initListener() {
        tvAirDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AirDetailActivity.startActivity(AirActivity.this);
            }
        });
    }

    //启动空气模块界面
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AirActivity.class);
        context.startActivity(intent);
    }
}
