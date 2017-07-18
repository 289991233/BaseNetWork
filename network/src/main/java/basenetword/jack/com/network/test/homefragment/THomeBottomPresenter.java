package basenetword.jack.com.network.test.homefragment;

import android.view.View;

import basenetword.jack.com.network.http.TBasePresenter;
import basenetword.jack.com.network.http.rxhttp.RequestCallBack;
import basenetword.jack.com.network.utils.baserecycleviewadapter.BaseQuickAdapter;

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

    @Override
    public void initClick(final TWelcomeEntity mWelcomeEntity, final THomeBottomAdapter mAdapter, final TFragmentController mController) {
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //1，全部设为未选择
                for (int i = 0; i < mWelcomeEntity.getList().getFooter_list().size(); i++) {
                    mWelcomeEntity.getList().getFooter_list().get(i).setIcon_true(1);
                }
                //2，设置选中状态
                mWelcomeEntity.getList().getFooter_list().get(position).setIcon_true(0);
                mController.showFragment(position);//3,显示对应的界面
                mAdapter.notifyDataSetChanged();//4,刷新底部
            }
        });
    }
}
