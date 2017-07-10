package basenetword.jack.com.network;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.sunfusheng.glideimageview.progress.GlideApp;

import basenetword.jack.com.network.utils.ninegridviewutils.NineGridView;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/7/7 11:31
 * 修订历史：
 * 修 改 人：
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        NineGridView.setImageLoader(new PicassoImageLoader());

    }

    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * Picasso 加载
     */
    private class PicassoImageLoader implements NineGridView.ImageLoader {

        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            GlideApp.with(context)
                    .load(url)
                    .placeholder(basenetword.jack.com.network.R.mipmap.ic_acquiescent)
                    .error(basenetword.jack.com.network.R.mipmap.ic_acquiescent)
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }
}
