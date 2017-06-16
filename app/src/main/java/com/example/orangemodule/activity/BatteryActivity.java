package com.example.orangemodule.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.orangemodule.R;
import com.example.orangemodule.bean.Battery;
import com.example.orangemodule.bean.Message;
import com.example.orangemodule.communication.UsbCommunication;
import com.example.orangemodule.utils.MessageUtils;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BatteryActivity extends AppCompatActivity {

    private static final String TAG = BatteryActivity.class.getSimpleName();
    @Bind(R.id.tv_battery)
    TextView tvBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        ButterKnife.bind(this);
        initView();
    }

    //初始化
    private void initView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                UsbCommunication usbCommunication = UsbCommunication.getInstance(BatteryActivity.this);
                usbCommunication.sendMessage(Message.sendBatteryBytes());
                String jsonData = MessageUtils.printData(usbCommunication.receiveMessage());
                Gson gson = new Gson();
                Battery battery = gson.fromJson(jsonData, Battery.class);
                tvBattery.setText(battery.getFrame_battery() + "%");
                Log.e(TAG, jsonData);
            }
        });
    }

    //启动电池界面
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, BatteryActivity.class);
        context.startActivity(intent);
    }
}
