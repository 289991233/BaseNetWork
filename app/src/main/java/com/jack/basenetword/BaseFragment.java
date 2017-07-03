package com.jack.basenetword;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/6/30 17:04
 * 修订历史：
 * 修 改 人：
 */
public abstract class BaseFragment<DB extends ViewDataBinding> extends Fragment {
    protected DB mBinding = null;
    protected BaseActivity mContext;
    // 控件是否初始化完成
    // 我们在控件初始化完成之后再进行数据的加载，否则对控件进行操作的时候会遇到空指针异常
    protected boolean isViewCreated;

    // 是否加载过数据
    // 我们判断未曾加载过数据的话再进行获取，否则每次对用户可见时都会执行懒加载的方法
    protected boolean isLoadCompleted;

    // 该方法只有在ViewPager与Fragment结合使用的时候才会执行
    // 该方法在onCreateView之前调用
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isLoadCompleted) {
            // 只有在对用户可见、控件初始化完成并且未曾加载过数据的情况下才进行懒加载
            lazyLoad();
            isLoadCompleted = true;
        }
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.mContext = (BaseActivity) context;
    }
// ViewPager的第一个Fragment默认执行setUserVisibleHint(fasle)方法
    // 所以在activity创建完成后要让第一页也加载数据


    // 懒加载,强制子类重写
    protected abstract void lazyLoad();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        init(savedInstanceState);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            // 此处不需要判断isViewCreated，因为这个方法在onCreateView方法之后执行
            lazyLoad();
            isLoadCompleted = true;
        }

    }

    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);
}
