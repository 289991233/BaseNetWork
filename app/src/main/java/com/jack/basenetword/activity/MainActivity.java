package com.jack.basenetword.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.jack.basenetword.R;
import com.jack.basenetword.base.XBaseActivity;
import com.jack.basenetword.databinding.ActivityMainBinding;
import com.jack.basenetword.entity.InformationclassEntity;
import com.jack.basenetword.entity.TabEntity;
import com.jack.basenetword.fragment.TestFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import basenetword.jack.com.network.http.rxhttp.OkHttp;
import basenetword.jack.com.network.utils.Loger;
import basenetword.jack.com.network.utils.ToastUtil;
import basenetword.jack.com.network.utils.rx.RxBus;
import basenetword.jack.com.network.utils.rx.Subscribe;
import okhttp3.Request;

public class MainActivity extends XBaseActivity<ActivityMainBinding> {
    private List<String> mStrings = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private InformationclassEntity mEntity = new InformationclassEntity();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        LSharePreference.getInstance(mContext).setString("ss", "To");
//        ToastUtil.getInstance().showToast(LSharePreference.getInstance(mContext).getString("ss").toString());

    }

    @Override
    protected void initView() {
        RxBus.getInstance().register(this);
        OkHttp.GetRequset("https://hssc.m.huisou.com/apps/news/category", 10087, new OkHttp.IHttpRequest() {
            @Override
            public void responseSucceed(int type, String s) {
                Loger.e(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.optString("code").equals("40000")) {
                        mEntity = new Gson().fromJson(s, InformationclassEntity.class);
                        for (int i = 0; i < mEntity.getList().size(); i++) {
                            TestFragment m = new TestFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("id", mEntity.getList().get(i).getCid());
                            bundle.putString("type", mEntity.getList().get(i).getType());
                            bundle.putString("flags", mEntity.getList().get(i).getFlags());
                            bundle.putString("name", mEntity.getList().get(i).getCname());
                            mStrings.add(mEntity.getList().get(i).getCname());
                            mTabEntities.add(new TabEntity(mEntity.getList().get(i).getCname()));
                            Loger.e(mEntity.getList().get(i).getCname());
                            m.setArguments(bundle);
                            mFragments.add(m);
                        }
                        mBinding.tbCommon.setTabData(mTabEntities);
                        mBinding.tbCommon.setOnTabSelectListener(new OnTabSelectListener() {
                            @Override
                            public void onTabSelect(int position) {
                                mBinding.vp.setCurrentItem(position);
                            }

                            @Override
                            public void onTabReselect(int position) {

                            }
                        });
                        mBinding.vp.setAdapter(new MyAdapter(getSupportFragmentManager(), mFragments, mStrings));
//                        mBinding.tab.setupWithViewPager(mBinding.vp);
//                        mBinding.vp.setOffscreenPageLimit(mFragments.size());
//                        setIndicator(mBinding.tab,10,10);
                        mBinding.vp.setCurrentItem(0);
                        mBinding.vp.setOffscreenPageLimit(mFragments.size());
                        mBinding.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                mBinding.tbCommon.setCurrentTab(position);
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });


                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void requestFailure(Request request, IOException e) {

            }
        });


    }

    @Override
    protected void initData() {

    }

    @Subscribe(code = 100)
    public void receive100(String msg) {
        if (msg.equals("1")) {
            mBinding.ll.setVisibility(View.VISIBLE);
        } else {
            mBinding.ll.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unRegister(this);
        Loger.e("退出");
    }

    class MyAdapter extends FragmentStatePagerAdapter {
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


    //    public void setIndicator(Activity activity, TabLayout tabs, int leftDip, int rightDip) {
//        Class<?> tabLayout = tabs.getClass();
//        Field tabStrip = null;
//        try {
//            tabStrip = tabLayout.getDeclaredField("mTabStrip");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        tabStrip.setAccessible(true);
//        LinearLayout ll_tab = null;
//        try {
//            ll_tab = (LinearLayout) tabStrip.get(tabs);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        int left =(getDisplayMetrics(activity).density * leftDip);
//    }
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    private long exitTime = 0;


    /**
     * 返回键监听建议使用  onBackPressed()
     * 不建议使用onKeyDown
     */
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.getInstance().showToast("再按一次退出程序");
            exitTime = System.currentTimeMillis();

        } else {
//            CommonUntil.copy("", context);
            System.exit(0);
            super.onBackPressed();

        }
    }
}
