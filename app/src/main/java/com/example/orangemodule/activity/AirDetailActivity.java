package com.example.orangemodule.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.orangemodule.R;
import com.example.orangemodule.bean.AirDetail;
import com.example.orangemodule.bean.Message;
import com.example.orangemodule.communication.UsbCommunication;
import com.example.orangemodule.utils.MessageUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tech.linjiang.suitlines.SuitLines;
import tech.linjiang.suitlines.Unit;

public class AirDetailActivity extends AppCompatActivity {

    private static final String TAG = AirDetailActivity.class.getSimpleName();
    @Bind(R.id.sl_AQI)
    SuitLines slAQI;
    @Bind(R.id.sl_PM)
    SuitLines slPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_detail);
        ButterKnife.bind(this);

        initView();
    }

    //初始化
    private void initView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                UsbCommunication communication = UsbCommunication.getInstance(AirDetailActivity.this);
                communication.sendMessage(Message.sendAllAirBytes());
                String jsonData = MessageUtils.printData(communication.receiveMessage());
                Log.e(TAG, jsonData);
                Gson gson = new Gson();
                AirDetail airDetail = gson.fromJson(jsonData, AirDetail.class);
                List<Unit> lines_aqi = new ArrayList<>();
                List<Unit> lines_pm = new ArrayList<>();
                for (int i = 0; i < 24; i++) {
                    lines_aqi.add(new Unit(airDetail.getAQI()[i], i + ""));
                    lines_pm.add(new Unit(airDetail.getPM25()[i], i + ""));
                }
                slAQI.feedWithAnim(lines_aqi);
                slPM.feedWithAnim(lines_pm);
            }
        });

    }

    //启动空气详细数据界面
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AirDetailActivity.class);
        context.startActivity(intent);
    }
}
