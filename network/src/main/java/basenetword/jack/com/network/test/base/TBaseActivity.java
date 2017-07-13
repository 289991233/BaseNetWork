package basenetword.jack.com.network.test.base;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import basenetword.jack.com.network.http.BaseView;
import basenetword.jack.com.network.http.GenericHelper;
import basenetword.jack.com.network.http.XBasePresenter;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 描    述：
 * 创 建 人：
 * 创建日期：2017/4/21 19:41
 * 修订历史：
 * 修 改 人：
 */
public abstract class TBaseActivity<DB extends ViewDataBinding, P extends XBasePresenter> extends AppCompatActivity implements BaseView {
    protected DB mBinding = null;
    protected Activity mContext;
    protected P mPresenter = null;
    //保存观察者和订阅者的订阅关系对象
    public CompositeDisposable httpCompositeDisposable = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mContext = this;
        try {
            mPresenter = GenericHelper.newPresenter(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mPresenter != null) {
            mPresenter.start();
        }
        initView();
        initData();
        init(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void Toast(String msg) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void initToolBar(Toolbar toolbar, String name) {

    }

    @Override
    public void initToolBar(Toolbar toolbar) {

    }

    @Override
    public void hideDialog() {

    }


//////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 使用Disposable 必须进行订阅管理
     *
     * @param disposable
     */
    protected void addDisposable(Disposable disposable) {
        if (httpCompositeDisposable == null) {
            httpCompositeDisposable = new CompositeDisposable();
        }
        httpCompositeDisposable.add(disposable);
    }

    /**
     * 解除compositeDisposable中所有保存的Disposable对象中的订阅关系
     */
    public void httpDispose() {
        if (httpCompositeDisposable != null) {
            httpCompositeDisposable.dispose();
            httpCompositeDisposable = null;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.end();
        }
        httpDispose();
    }
}
