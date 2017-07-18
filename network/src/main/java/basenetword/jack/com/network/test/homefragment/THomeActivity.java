package basenetword.jack.com.network.test.homefragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import basenetword.jack.com.network.R;
import basenetword.jack.com.network.databinding.ActivityThomeBinding;
import basenetword.jack.com.network.test.base.TBaseActivity;

/**
 * 描    述：程序主框架界面
 * 创 建 人：SDX
 * 创建日期：2017/7/14 14:31
 * 修订历史：
 * 修 改 人：
 */
public class THomeActivity extends TBaseActivity<ActivityThomeBinding, THomeBottomPresenter> implements THomeBottomContract.View {
    private TWelcomeEntity mWelcomeEntity = null;
    private TFragmentController mController = null;
    private THomeBottomAdapter mAdapter = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thome;
    }

    @Override
    protected void initView() {
        //初始化UI控件 数据
        mWelcomeEntity = new TWelcomeEntity();
    }

    @Override
    protected void initData() {
        //请求strat接口数据
        showDialog();
        mPresenter.getStart();
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    /**
     * 底部栏切换操作
     * 放在初始化后面
     */
    private void initClick() {
        mPresenter.initClick(mWelcomeEntity, mAdapter, mController);
    }

    private void initInfo() {
        //初始化界面
        mController = TFragmentController.getInstance(this, R.id.fl_content, true, mWelcomeEntity);
        //个数有后台控制
        mBinding.rv.setLayoutManager(new GridLayoutManager(mContext, mWelcomeEntity.getList().getFooter_list().size()));
        mAdapter = new THomeBottomAdapter(mContext, mWelcomeEntity.getList().getFooter_list());
        mBinding.rv.setAdapter(mAdapter);
        mController.showFragment(); //初始化显示界面是位置
        initClick();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mController.onDestroy();//退出释放
    }

    /**
     * 防止出现重叠情况
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
        // 将这一行注释掉，阻止activity保存fragment的状态
    }

    @Override
    public void onSuccess(TWelcomeEntity welcomeEntity, int type) {
        hideDialog();
        mWelcomeEntity = welcomeEntity;
        initInfo();
    }

    @Override
    public void onError(Throwable t, String errorTips) {
        hideDialog();
    }
}
