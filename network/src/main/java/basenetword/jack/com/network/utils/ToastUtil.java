package basenetword.jack.com.network.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import basenetword.jack.com.network.R;


public class ToastUtil {
    public Context context;
    private TextView tv;
    private Toast result;
    private static ToastUtil toastUtil;

    public static ToastUtil getInstance() {
        if (toastUtil == null) {
            synchronized (ToastUtil.class) {
                if (toastUtil == null)
                    toastUtil = new ToastUtil(Utils.getContext());
            }
        }
        return toastUtil;
    }


    public ToastUtil(Context context) {
        this.context = context;
    }

    public void showToast(CharSequence text) {
        if (!TextUtils.isEmpty(text))
            makeText(text, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int resId) {
        makeText(resId, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(CharSequence text) {
        if (!TextUtils.isEmpty(text))
            makeText(text, Toast.LENGTH_LONG).show();
    }

    public void showLongToast(int resId) {
        makeText(resId, Toast.LENGTH_LONG).show();
    }

    private Toast makeText(CharSequence text, int duration) {
        if (result == null) {
            result = new Toast(context);
            LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflate.inflate(R.layout.toast_ui, null);
            tv = (TextView) v.findViewById(R.id.toast);
            result.setView(v);
            //setGravity方法用于设置位置，此处为垂直居中
            result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            result.setDuration(duration);
        }
        tv.setText(text);
        return result;
    }

    private Toast makeText(int resId, int duration) {
        if (result == null) {
            result = new Toast(context);
            LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflate.inflate(R.layout.toast_ui, null);
            tv = (TextView) v.findViewById(R.id.toast);
            result.setView(v);
            //setGravity方法用于设置位置，此处为垂直居中
            result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            result.setDuration(duration);
        }
        tv.setText(resId);
        return result;
    }
}
