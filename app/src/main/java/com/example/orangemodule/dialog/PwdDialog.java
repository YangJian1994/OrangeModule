package com.example.orangemodule.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.orangemodule.R;
import com.example.orangemodule.activity.UDiskActivity;
import com.example.orangemodule.widget.PwdEditText;

/**
 * Created by 杨健 on 2017/6/6.
 * function: U盘密码dialog
 */

public class PwdDialog extends BaseDialog {

    private PwdEditText pwdEditText;

    public PwdDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.psw_dialog_layout);
        pwdEditText = (PwdEditText) findViewById(R.id.pwdEditText);

        pwdEditText.initStyle(R.drawable.edit_num_bg_red, 6, 0.33f, R.color.colorAccent, R.color.colorAccent, 20);
        pwdEditText.setOnTextFinishListener(new PwdEditText.OnTextFinishListener() {
            @Override
            public void onFinish(String str) {
                //密码输入完后的回调
                if (str.equals("123456")) {
                    Log.e("password", str);
                    UDiskActivity.startActivity(context);
                    
                } else {
                    Log.e("password", str);
                    Toast.makeText(context, "密码不正确，请再次输入。", Toast.LENGTH_SHORT).show();
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pwdEditText.setFocus();
            }
        }, 100);

    }
}
