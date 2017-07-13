package com.jack.basenetword.fragment;

import android.os.Bundle;

import com.jack.basenetword.base.XBaseFragment;
import com.jack.basenetword.R;
import com.jack.basenetword.databinding.FargmentTest1Binding;

import java.io.IOException;

import basenetword.jack.com.network.http.rxhttp.OkHttp;
import basenetword.jack.com.network.utils.dialog.LoagDialog;
import okhttp3.Request;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/7/3 10:04
 * 修订历史：
 * 修 改 人：
 */
public class Test1Fragment extends XBaseFragment<FargmentTest1Binding> {
    @Override
    protected void lazyLoad() {
        LoagDialog.getInstance(getActivity()).showDialog();
        OkHttp.GetRequset("apps/start/index", 1, new OkHttp.IHttpRequest() {
            @Override
            public void responseSucceed(int type, String s) throws Exception {
                LoagDialog.getInstance(getActivity()).hideDialog();
            }

            @Override
            public void requestFailure(Request request, IOException e) {
                LoagDialog.getInstance(getActivity()).hideDialog();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fargment_test1;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        isViewCreated = true; // 控件初始化完成
    }
}
