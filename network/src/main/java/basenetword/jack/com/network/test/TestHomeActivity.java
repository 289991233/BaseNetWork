package basenetword.jack.com.network.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import basenetword.jack.com.network.R;
import basenetword.jack.com.network.databinding.ActivityTestHomeBinding;
import basenetword.jack.com.network.test.activity.SelcetImageActivity;
import basenetword.jack.com.network.test.activity.SimpeImaActivity;
import basenetword.jack.com.network.test.activity.SimpeImage2Activity;
import basenetword.jack.com.network.test.activity.VlayoutActivity;
import basenetword.jack.com.network.test.base.TBaseActivity;
import basenetword.jack.com.network.test.mvptest.TestHomeContract;
import basenetword.jack.com.network.test.mvptest.TestHomePresenter;
import basenetword.jack.com.network.test.nice9.MainActivity;

public class TestHomeActivity extends TBaseActivity<ActivityTestHomeBinding, TestHomePresenter> implements TestHomeContract.View {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initClick();
    }

    private void initClick() {
        mBinding.btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, SelcetImageActivity.class));
            }
        });
        mBinding.btnImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, SimpeImaActivity.class));
            }
        });
        mBinding.btnImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, SimpeImage2Activity.class));
            }
        });
        mBinding.btnImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MainActivity.class));
            }
        });
        mBinding.btnImg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, VlayoutActivity.class));
            }
        });
        mBinding.btnImg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                mPresenter.getHome("1", 1);
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onSuccess(String s, int type) {
        hideDialog();
        mBinding.tv.setText(s);
    }

    @Override
    public void onError(Throwable t, String errorTips) {
        hideDialog();
        mBinding.tv.setText(errorTips + "---" + t);
    }
}
