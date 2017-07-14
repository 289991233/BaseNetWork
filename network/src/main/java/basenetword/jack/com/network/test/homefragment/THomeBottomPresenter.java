package basenetword.jack.com.network.test.homefragment;

import basenetword.jack.com.network.http.TBasePresenter;
import basenetword.jack.com.network.http.rxhttp.RequestCallBack;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/7/14 14:54
 * 修订历史：
 * 修 改 人：
 */
public class THomeBottomPresenter extends TBasePresenter<THomeBottomContract.View, THomeBottomModel> implements THomeBottomContract.Presenter {
    @Override
    public void getStart() {
        model.getStart(new RequestCallBack<TWelcomeEntity>() {
            @Override
            public void onSuccess(TWelcomeEntity data, int type) {
                view.onSuccess(data, 0);
            }

            @Override
            public void onError(Throwable t, String errorTips) {
                view.onError(t, errorTips);
            }
        });
    }
}
