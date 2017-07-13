package basenetword.jack.com.network.http;

import android.support.v7.widget.Toolbar;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/6/19 10:09
 * 修订历史：
 * 修 改 人：
 */
public interface BaseView {

    void Toast(String msg);//显示taost

    void showDialog();  //显示弹框

    void initToolBar(Toolbar toolbar, String name);

    void initToolBar(Toolbar toolbar);

    void hideDialog(); //关闭弹框
}
