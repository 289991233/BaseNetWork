package basenetword.jack.com.network.test.mvptest;

import basenetword.jack.com.network.http.Constants.RxUtil;
import basenetword.jack.com.network.http.models.TBaseModel;
import basenetword.jack.com.network.http.rxhttp.BaseObserver;
import basenetword.jack.com.network.http.rxhttp.OkRetrofit;
import basenetword.jack.com.network.http.rxhttp.RequestCallBack;
import basenetword.jack.com.network.okhttp.RxHttpUtils;
import basenetword.jack.com.network.okhttp.base.BaseResponse;
import basenetword.jack.com.network.okhttp.http.CommonObserver;
import basenetword.jack.com.network.okhttp.interceptor.Transformer;
import basenetword.jack.com.network.test.TApiServer;
import basenetword.jack.com.network.test.homefragment.TWelcomeEntity;
import basenetword.jack.com.network.utils.Loger;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/7/13 14:06
 * 修订历史：
 * 修 改 人：
 */
public class TestHomeModel extends TBaseModel implements TestHomeContract.Model {


    @Override
    public void getHome(String params, final int type, final RequestCallBack requestCallBack) {

        Disposable disposable = OkRetrofit
                .getInstance()
                .getApi()
                .getTestData1(params)
                .compose(RxUtil.<String>applySchedulers())
                .subscribeWith(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(String s) throws Exception {
                        requestCallBack.onSuccess(s, type);
                    }

                    @Override
                    public void onError(Throwable t, String errorTips) {
                        requestCallBack.onError(t, errorTips);
                    }
                });

        addDisposable(disposable);


    }
}
