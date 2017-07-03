package com.jack.basenetword;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.gson.Gson;
import com.jack.basenetword.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import basenetword.jack.com.network.http.OkHttp;
import basenetword.jack.com.network.utils.Loger;
import okhttp3.Request;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private List<String> mStrings = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private InformationclassEntity mEntity = new InformationclassEntity();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        OkHttp.GetRequset("apps/news/category", 1, new OkHttp.IHttpRequest() {
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
                            Loger.e(mEntity.getList().get(i).getCname());
                            m.setArguments(bundle);
                            mFragments.add(m);
                        }
                        mBinding.vp.setAdapter(new MyAdapter(getSupportFragmentManager(), mFragments, mStrings));
                        mBinding.tab.setupWithViewPager(mBinding.vp);
                        mBinding.vp.setOffscreenPageLimit(mFragments.size());
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
