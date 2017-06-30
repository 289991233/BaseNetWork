package com.jack.basenetword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import basenetword.jack.com.network.utils.BaseDialog;
import basenetword.jack.com.network.utils.ToastUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToastUtil.getInstance(this).showToast("ss");
        BaseDialog.getInstance(this).showDialog();
    }
}
