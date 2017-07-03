package com.jack.basenetword;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 描    述：
 * 创 建 人：
 * 创建日期：2017/4/21 19:41
 * 修订历史：
 * 修 改 人：
 */
public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity {
    protected DB mBinding = null;
    protected Activity mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mContext = this;
        init(savedInstanceState);
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void initData();
}
