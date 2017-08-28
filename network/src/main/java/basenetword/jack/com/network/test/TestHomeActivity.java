package basenetword.jack.com.network.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import basenetword.jack.com.network.R;
import basenetword.jack.com.network.databinding.ActivityTestHomeBinding;
import basenetword.jack.com.network.okhttp.RxHttpUtils;
import basenetword.jack.com.network.okhttp.http.CommonObserver;
import basenetword.jack.com.network.okhttp.interceptor.Transformer;
import basenetword.jack.com.network.test.activity.SelcetImageActivity;
import basenetword.jack.com.network.test.activity.SimpeImaActivity;
import basenetword.jack.com.network.test.activity.SimpeImage2Activity;
import basenetword.jack.com.network.test.activity.Vlayout2Activity;
import basenetword.jack.com.network.test.base.TBaseActivity;
import basenetword.jack.com.network.test.homefragment.THomeActivity;
import basenetword.jack.com.network.test.mvptest.HomeNewEntity;
import basenetword.jack.com.network.test.mvptest.TestHomeContract;
import basenetword.jack.com.network.test.mvptest.TestHomePresenter;
import basenetword.jack.com.network.test.nice9.MainActivity;
import io.reactivex.disposables.Disposable;

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
                startActivity(new Intent(mContext, Vlayout2Activity.class));
            }
        });
        mBinding.btnImg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
                mPresenter.getHome("1", 1);
            }
        });

        mBinding.btnImg6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, THomeActivity.class));
            }
        });

        mBinding.btnImg7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxHttpUtils
                        .getSInstance()
                        .baseUrl("https://hssc.m.huisou.com/")
                        .createSApi(TApiServer.class)
                        .getTestData2("1")
                        .compose(Transformer.<HomeNewEntity>switchSchedulers())
                        .subscribe(new CommonObserver<HomeNewEntity>() {
                            @Override
                            protected void getDisposable(Disposable d) {
                                //方法暴露出来使用者根据需求去取消订阅
                                d.dispose();//在onDestroy方法中调用
                            }

                            @Override
                            protected void onError(String errorMsg) {
                                //错误处理
                            }

                            @Override
                            protected void onSuccess(HomeNewEntity bookBean) {
                                //业务处理
                                mBinding.tv.setText(bookBean.getCode());
                            }
                        });
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
