package basenetword.jack.com.network.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import basenetword.jack.com.network.R;
import basenetword.jack.com.network.databinding.ActivityTestHomeBinding;
import basenetword.jack.com.network.test.activity.SelcetImageActivity;
import basenetword.jack.com.network.test.activity.SimpeImaActivity;
import basenetword.jack.com.network.test.activity.SimpeImage2Activity;
import basenetword.jack.com.network.test.base.TBaseActivity;

public class TestHomeActivity extends TBaseActivity<ActivityTestHomeBinding> {


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
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}