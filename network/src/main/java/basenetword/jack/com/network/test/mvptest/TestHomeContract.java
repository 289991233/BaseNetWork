package basenetword.jack.com.network.test.mvptest;

import basenetword.jack.com.network.http.TContract;
import basenetword.jack.com.network.http.rxhttp.RequestCallBack;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/7/13 14:07
 * 修订历史：
 * 修 改 人：
 */
public interface TestHomeContract {
    interface View extends TContract.View {
        void onSuccess(String s, int type);

        void onError(Throwable t, String errorTips);
    }

    interface Presenter extends TContract.Presenter {
        void getHome(String oage, int type);
    }

    interface Model extends TContract.Model {
        void getHome(String params, int type, RequestCallBack requestCallBack);
    }
}
