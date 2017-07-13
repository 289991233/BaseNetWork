package com.jack.basenetword.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jack.basenetword.R;
import com.jack.basenetword.adapter.TestAdapter;
import com.jack.basenetword.base.XBaseFragment;
import com.jack.basenetword.databinding.FragmentTestBinding;
import com.jack.basenetword.entity.NewEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import basenetword.jack.com.network.http.rxhttp.OkHttp;
import basenetword.jack.com.network.test.TestHomeActivity;
import basenetword.jack.com.network.utils.Loger;
import basenetword.jack.com.network.utils.ToastUtil;
import basenetword.jack.com.network.utils.dialog.LoagDialog;
import okhttp3.Request;

/**
 * 描    述：//// 这行代码一定要写，否则会出现奇葩问题viewPager.setOffscreenPageLimit(fragments.size());
 * 创 建 人：SDX
 * 创建日期：2017/6/30 17:10
 * 修订历史：
 * 修 改 人：
 */
public class TestFragment extends XBaseFragment<FragmentTestBinding> {
    private List<String> mStrings = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private String flags = "";
    private String id = "";
    private String type = "";
    private int page = 1;
    private TestAdapter mTestAdapter = null;
    private NewEntity mEntity = new NewEntity();
    boolean loading;

    @Override
    protected void lazyLoad() {
        initDatas();
//        mBinding.rv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        mBinding.rv.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
//        mBinding.rv.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                page = 1;
//                mTestAdapter.clear();
//                initDatas();
//            }
//
//            @Override
//            public void onLoadMore() {
//                page++;
//                initDatas();
//
//            }
//        });

        mBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, TestHomeActivity.class));
            }
        });
        mBinding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mTestAdapter.clear();
                loading = false;
                page = 1;
                initDatas();
            }
        });
        mBinding.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!loading && !recyclerView.canScrollVertically(1)) {
                    loading = true;
                    page++;
                    initDatas();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Loger.e(dx + "---" + dy);
            }
        });

    }

    private void initDatas() {

        LoagDialog.getInstance(getActivity()).showDialog();
        String url = "apps/news/index?type=" + type + "&flags=" + flags + "&page=" + page + "&categoryid=" + id + "&token=";
        OkHttp.GetRequset(url, 1, new OkHttp.IHttpRequest() {
            @Override
            public void responseSucceed(int type, String s) {
mBinding.swipe.setRefreshing(false);

                LoagDialog.getInstance(getActivity()).hideDialog();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    mEntity = new Gson().fromJson(s, NewEntity.class);
                    if (jsonObject.optString("code").equals("40000")) {
                        if (page == 1) {
                            mTestAdapter = new TestAdapter(mContext, mEntity.getList().getNewslist());
//                            mTestAdapter.setNewData(mEntity.getList().getNewslist());
                            mBinding.rv.setAdapter(mTestAdapter);
//                            mBinding.rv.refreshComplete();
                        } else {
                            loading = false;
                            mTestAdapter.addData(mEntity.getList().getNewslist());
                            if (mEntity.getList().getNewslist().size() == 0) {
                                loading = true;
//                                mBinding.rv.loadMoreComplete();
//            View view = View.inflate(mContext, R.layout.activity_mainnice, null);
//                                mTestAdapter.addFooterView(view);
                            }
//                            mBinding.rv.loadMoreComplete();
                        }


                    } else {
                        ToastUtil.getInstance().showToast(jsonObject.optString("hint").toString());

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void requestFailure(Request request, IOException e) {
                LoagDialog.getInstance(getActivity()).hideDialog();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        initView();
        mBinding.rv.setLayoutManager(new LinearLayoutManager(mContext));
        isViewCreated = true; // 控件初始化完成
    }

    private void initView() {
        mStrings.add("Ftab1");
        mStrings.add("Ftab2");
        mStrings.add("Ftab3");
        mStrings.add("Ftab4");
        mFragments.add(new Test1Fragment());
        mFragments.add(new Test1Fragment());
        mFragments.add(new Test1Fragment());
        mFragments.add(new Test1Fragment());
        mBinding.vp.setAdapter(new MyAdapter(getChildFragmentManager(), mFragments, mStrings));
        mBinding.tab.setupWithViewPager(mBinding.vp);
        mBinding.vp.setOffscreenPageLimit(mFragments.size());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString("id").toString();
            flags = getArguments().get("flags").toString();
            type = getArguments().getString("type").toString();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Loger.e("Fragment退出");
    }

    class MyAdapter extends FragmentPagerAdapter {
        private List<String> mStrings;
        private List<Fragment> mFragments;

        public MyAdapter(FragmentManager fm, List<Fragment> fragments, List<String> strings) {
            super(fm);
            this.mStrings = strings;
            this.mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mStrings.get(position);
        }
    }
}
