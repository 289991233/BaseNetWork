package basenetword.jack.com.network.test.mvptest;

import basenetword.jack.com.network.http.TBasePresenter;
import basenetword.jack.com.network.http.rxhttp.RequestCallBack;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/7/13 14:09
 * 修订历史：
 * 修 改 人：
 */
public class TestHomePresenter extends TBasePresenter<TestHomeContract.View, TestHomeModel> implements TestHomeContract.Presenter {
    @Override
    public void getHome(String oage, int type) {
        model.getHome(oage, type, new RequestCallBack<String>() {

            @Override
            public void onSuccess(String data, int type) {
                view.onSuccess(data, type);
            }

            @Override
            public void onError(Throwable t, String errorTips) {
                view.onError(t, errorTips);
            }
        });
    }
}
