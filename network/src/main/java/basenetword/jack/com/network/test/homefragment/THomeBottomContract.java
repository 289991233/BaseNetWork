package basenetword.jack.com.network.test.homefragment;

import basenetword.jack.com.network.http.TContract;
import basenetword.jack.com.network.http.rxhttp.RequestCallBack;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/7/14 14:49
 * 修订历史：
 * 修 改 人：
 */
public interface THomeBottomContract {
    interface View extends TContract.View {
        void onSuccess(TWelcomeEntity welcomeEntity, int type);

        void onError(Throwable t, String errorTips);
    }

    interface Presenter extends TContract.Presenter {
        void getStart();

        void initClick(TWelcomeEntity welcomeEntity, THomeBottomAdapter adapter, TFragmentController controller);
    }

    interface Model extends TContract.Model {
        void getStart(RequestCallBack requestCallBack);
    }
}
