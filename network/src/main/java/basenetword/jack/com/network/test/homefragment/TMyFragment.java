package basenetword.jack.com.network.test.homefragment;


import android.os.Bundle;

import basenetword.jack.com.network.R;
import basenetword.jack.com.network.http.Constants.RxUtil;
import basenetword.jack.com.network.http.rxhttp.BaseObserver;
import basenetword.jack.com.network.http.rxhttp.OkRetrofit;
import basenetword.jack.com.network.test.base.TBaseFragment;

/**
 * 描    述：我的界面
 * 创 建 人：SDX
 * 创建日期：2017/7/14 9:45
 * 修订历史：
 * 修 改 人：
 */
public class TMyFragment extends TBaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tmy;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        isViewCreated = true;
    }

    @Override
    protected void initData() {
        showDialog();
        OkRetrofit.getInstance()
                .getApi()
                .getStartData1()
                .compose(RxUtil.<String>applySchedulers())
                .subscribeWith(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(String s) throws Exception {
                        hideDialog();
                    }


                    @Override
                    public void onError(Throwable t, String errorTips) {

                    }
                });
    }
}
