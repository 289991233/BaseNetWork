package basenetword.jack.com.network.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.RecyclablePagerAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelperEx;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;

import java.util.LinkedList;
import java.util.List;

import basenetword.jack.com.network.R;
import basenetword.jack.com.network.databinding.ActivityVlayoutBinding;
import basenetword.jack.com.network.test.base.NullPresenter;
import basenetword.jack.com.network.test.base.TBaseActivity;

public class VlayoutActivity extends TBaseActivity<ActivityVlayoutBinding,NullPresenter> {
    private static final boolean BANNER_LAYOUT = true;

    private static final boolean LINEAR_LAYOUT = true;

    private static final boolean ONEN_LAYOUT = true;

    private static final boolean GRID_LAYOUT = true;

    private static final boolean STICKY_LAYOUT = true;

    private static final boolean HORIZONTAL_SCROLL_LAYOUT = true;

    private static final boolean SCROLL_FIX_LAYOUT = true;
    private Runnable trigger;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vlayout;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        mBinding.mainView.setLayoutManager(layoutManager);
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        mBinding.mainView.setRecycledViewPool(viewPool);

        // recyclerView.addItemDecoration(itemDecoration);

        viewPool.setMaxRecycledViews(0, 20);

        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);

        mBinding.mainView.setAdapter(delegateAdapter);

        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 5));
        }

        if (ONEN_LAYOUT) {
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            helper.setBgColor(0xff876384);
            helper.setMargin(0, 10, 0, 10);
            helper.setColWeights(new float[]{40f, 45f, 15f, 60f, 0f});
            adapters.add(new SubAdapter(this, helper, 5));
        }
        if (BANNER_LAYOUT) {
            adapters.add(new SubAdapter(this, new LinearLayoutHelper(), 1) {

                @Override
                public void onViewRecycled(MainViewHolder holder) {
                    if (holder.itemView instanceof ViewPager) {
                        ((ViewPager) holder.itemView).setAdapter(null);
                    }
                }

                @Override
                public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    if (viewType == 1)
                        return new MainViewHolder(
                                LayoutInflater.from(mContext).inflate(R.layout.view_pager, parent, false));

                    return super.onCreateViewHolder(parent, viewType);
                }

                @Override
                public int getItemViewType(int position) {
                    return 1;
                }

                @Override
                protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {

                }

                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    if (holder.itemView instanceof ViewPager) {
                        ViewPager viewPager = (ViewPager) holder.itemView;

                        viewPager.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));

                        // from position to get adapter
                        viewPager.setAdapter(new PagerAdapter(this, viewPool));
                    }
                }
            });
        }

        //右下角的返回顶部
        if (SCROLL_FIX_LAYOUT) {
            ScrollFixLayoutHelper layoutHelper = new ScrollFixLayoutHelper(FixLayoutHelper.BOTTOM_RIGHT, 20, 20);
            layoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_LEAVE);
            adapters.add(new SubAdapter(this, layoutHelper, 1) {
                @Override
                public void onBindViewHolder(MainViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(50, 50);
                    holder.itemView.setLayoutParams(layoutParams);
                }
            });
        }


        if (LINEAR_LAYOUT) {
            adapters.add(new SubAdapter(this, new LinearLayoutHelper(), 100));
        }

        delegateAdapter.setAdapters(adapters);
//
//        final Handler mainHandler = new Handler(Looper.getMainLooper());
//
//        trigger = new Runnable() {
//            @Override
//            public void run() {
//                // recyclerView.scrollToPosition(22);
//                // recyclerView.getAdapter().notifyDataSetChanged();
//                mBinding.mainView.requestLayout();
//                // mainHandler.postDelayed(trigger, 1000);
//            }
//        };
//
//
//        mainHandler.postDelayed(trigger, 1000);
    }

    @Override
    protected void initData() {

    }

    static class PagerAdapter extends RecyclablePagerAdapter<MainViewHolder> {
        public PagerAdapter(SubAdapter adapter, RecyclerView.RecycledViewPool pool) {
            super(adapter, pool);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public void onBindViewHolder(MainViewHolder viewHolder, int position) {
            // only vertical
            viewHolder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ((TextView) viewHolder.itemView.findViewById(R.id.title)).setText("Banner: " + position);
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }
    }


    static class SubAdapter extends DelegateAdapter.Adapter<MainViewHolder> {

        private Context mContext;

        private LayoutHelper mLayoutHelper;


        private VirtualLayoutManager.LayoutParams mLayoutParams;
        private int mCount = 0;


        public SubAdapter(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        }

        public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
            this.mContext = context;
            this.mLayoutHelper = layoutHelper;
            this.mCount = count;
            this.mLayoutParams = layoutParams;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            // only vertical
            holder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(mLayoutParams));
        }


        @Override
        protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
            ((TextView) holder.itemView.findViewById(R.id.title)).setText(Integer.toString(offsetTotal));
        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }


    static class MainViewHolder extends RecyclerView.ViewHolder {

        public static volatile int existing = 0;
        public static int createdTimes = 0;

        public MainViewHolder(View itemView) {
            super(itemView);
            createdTimes++;
            existing++;
        }

        @Override
        protected void finalize() throws Throwable {
            existing--;
            super.finalize();
        }
    }
}
