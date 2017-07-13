package basenetword.jack.com.network.utils;

import android.content.Context;
import android.widget.ImageView;

import com.sunfusheng.glideimageview.progress.GlideApp;

import basenetword.jack.com.network.R;


/**
 * 描    述：  参考网站：https://github.com/sfsheng0322/GlideImageView
 * 创 建 人：SDX
 * 创建日期：2017/6/21 9:59
 * 修订历史：
 * 修 改 人：
 */
public class ImageLoad {

    /**
     * //原生 API
     *
     * @param context
     * @param url
     * @param iv
     */
    public static void load(Context context, ImageView iv, String url) {
        if (iv == null) {
            throw new IllegalArgumentException("argument error");
        }
        GlideApp.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_acquiescent)
                .error(R.mipmap.ic_acquiescent)
                .into(iv);
    }

    public static void load(Context context, ImageView iv, String url, int w, int h) {
        if (iv == null) {
            throw new IllegalArgumentException("argument error");
        }
        GlideApp.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_acquiescent)
                .error(R.mipmap.ic_acquiescent)
                .override(w, h).into(iv);
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////

}