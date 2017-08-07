package basenetword.jack.com.network.test.activity;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.DefaultLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;

import java.util.LinkedList;
import java.util.List;

import basenetword.jack.com.network.R;
import basenetword.jack.com.network.databinding.ActivityVlayout2Binding;
import basenetword.jack.com.network.test.base.NullPresenter;
import basenetword.jack.com.network.test.base.TBaseActivity;

public class Vlayout2Activity extends TBaseActivity<ActivityVlayout2Binding, NullPresenter> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_vlayout2;
    }

    @Override
    protected void initView() {
        //第一步：定义布局管理器
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(mContext);
        //RecyclerView设置布局管理
        mBinding.rv.setLayoutManager(virtualLayoutManager);
        mBinding.rv.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(10, 10, 10, 10);
            }
        });
        final List<LayoutHelper> helpers = new LinkedList<>();

        final GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        gridLayoutHelper.setItemCount(50);

//
//        final ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(FixLayoutHelper.TOP_RIGHT, 100, 100);
//
//        helpers.add(DefaultLayoutHelper.newHelper(17));
//        helpers.add(scrollFixLayoutHelper);
//        helpers.add(DefaultLayoutHelper.newHelper(12));
        helpers.add(gridLayoutHelper);

        virtualLayoutManager.setLayoutHelpers(helpers);
        mBinding.rv.setAdapter(
                new VirtualLayoutAdapter(virtualLayoutManager) {
                    @Override
                    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        return new MainViewHolder(new TextView(mContext));
                    }

                    @Override
                    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//                        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(
//                                ViewGroup.LayoutParams.MATCH_PARENT, 300);
//                        holder.itemView.setLayoutParams(layoutParams);
//
//                        ((TextView) holder.itemView).setText(Integer.toString(position));
                        holder.itemView.setBackgroundColor(0xccff00ff);
//                        if (position == 7) {
//                            layoutParams.height = 60;
//                            layoutParams.width = 60;
//                        } else if (position > 35) {
//                            layoutParams.height = 200 + (position - 30) * 10;
//                        }
//
//                        if (position > 35) {
//                            holder.itemView.setBackgroundColor(0x66cc0000 + (position - 30) * 128);
//                        } else if (position % 2 == 0) {
//                            holder.itemView.setBackgroundColor(0xaa00ff00);
//                        } else {
//                            holder.itemView.setBackgroundColor(0xccff00ff);
//                        }
                    }

                    @Override
                    public int getItemCount() {
                        List<LayoutHelper> helpers = getLayoutHelpers();
                        if (helpers == null) {
                            return 0;
                        }
                        int count = 0;
                        for (int i = 0, size = helpers.size(); i < size; i++) {
                            count += helpers.get(i).getItemCount();
                        }
                        return count;
                    }
                });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
    static class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(View itemView) {
            super(itemView);
        }
    }
}
