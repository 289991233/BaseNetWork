package basenetword.jack.com.network.test.activity;

import android.content.Intent;
import android.os.Bundle;

import com.luck.picture.lib.config.PictureConfig;

import basenetword.jack.com.network.R;
import basenetword.jack.com.network.databinding.ActivitySimpeImage2Binding;
import basenetword.jack.com.network.test.base.TBaseActivity;

public class SimpeImage2Activity extends TBaseActivity<ActivitySimpeImage2Binding> {

    SelectImage mSelectImage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_simpe_image2;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        mSelectImage = new SelectImage(mContext, mBinding.recycler, 5);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    mSelectImage.initData(data);
                    break;
            }
        }
    }
}
