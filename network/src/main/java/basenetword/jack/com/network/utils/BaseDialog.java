package basenetword.jack.com.network.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import basenetword.jack.com.network.R;


/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/6/20 14:47
 * 修订历史：
 * 修 改 人：
 */
public class BaseDialog {

    private static BaseDialog mBaseDialog;
    private Dialog dialog;

    public static BaseDialog getInstance(Activity activity) {
        if (mBaseDialog == null) {
            synchronized (BaseDialog.class) {
                if (mBaseDialog == null) {
                    mBaseDialog = new BaseDialog(activity);
                }
            }
        }
        return mBaseDialog;
    }


    public BaseDialog(Context context) {

        dialog = new Dialog(context, R.style.dialog_load);
        dialog.setContentView(R.layout.dialog_load);
        dialog.setCanceledOnTouchOutside(false);
    }

    public void showDialog() {
        dialog.setContentView(R.layout.dialog_load);
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public void hideDialog() {
        if (dialog.isShowing()) {
            dialog.dismiss();
            dialog.cancel();
        }
    }

}
