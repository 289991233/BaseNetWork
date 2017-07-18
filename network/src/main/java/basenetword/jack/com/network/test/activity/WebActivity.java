package basenetword.jack.com.network.test.activity;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;

import basenetword.jack.com.network.R;
import basenetword.jack.com.network.databinding.ActivityWebBinding;
import basenetword.jack.com.network.test.base.NullPresenter;
import basenetword.jack.com.network.test.base.TBaseActivity;

public class WebActivity extends TBaseActivity<ActivityWebBinding, NullPresenter> {
    private WebSettings settings;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        settings = mBinding.webview.getSettings();
        //解决hppts加载不出图片原因
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);//将图片调整到适合WebView的大小
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true); //如果访问的页面中有Javascript，则WebView必须设置支持Javascript
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDatabaseEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setBlockNetworkImage(true);

        settings.setSupportZoom(true);//支持缩放
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setDisplayZoomControls(false);//是否显示缩放按钮
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
