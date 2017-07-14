package com.jack.basenetword.base;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import basenetword.jack.com.network.utils.statusbar.ImmersionBar;

/**
 * 描    述：
 * 创 建 人：
 * 创建日期：2017/4/21 19:41
 * 修订历史：
 * 修 改 人：
 */
public abstract class XBaseActivity<DB extends ViewDataBinding> extends AppCompatActivity {
    protected DB mBinding = null;
    protected Activity mActivity;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mContext = getApplicationContext();
        this.mActivity = this;
        ImmersionBar.with(this).statusBarColor("#FF0000").init();
        init(savedInstanceState);
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        System.exit(0);
    }
}
