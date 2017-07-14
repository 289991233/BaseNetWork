package basenetword.jack.com.network.test.homefragment;

import basenetword.jack.com.network.http.Constants.RxUtil;
import basenetword.jack.com.network.http.rxhttp.BaseObserver;
import basenetword.jack.com.network.http.rxhttp.OkRetrofit;
import basenetword.jack.com.network.http.rxhttp.RequestCallBack;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/7/14 14:51
 * 修订历史：
 * 修 改 人：
 */
public class THomeBottomModel implements THomeBottomContract.Model {

    @Override
    public void getStart(final RequestCallBack requestCallBack) {
        OkRetrofit.getInstance()
                .getApi()
                .getStartData()
                .compose(RxUtil.<TWelcomeEntity>applySchedulers())
                .subscribeWith(new BaseObserver<TWelcomeEntity>() {
                    @Override
                    public void onSuccess(TWelcomeEntity s) throws Exception {
                        requestCallBack.onSuccess(s, 0);
                    }


                    @Override
                    public void onError(Throwable t, String errorTips) {
                        requestCallBack.onError(t, errorTips);
                    }
                });
    }
}
