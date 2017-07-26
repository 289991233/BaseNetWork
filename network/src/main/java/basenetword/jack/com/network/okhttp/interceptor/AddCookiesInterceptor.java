package basenetword.jack.com.network.okhttp.interceptor;

import android.util.Log;


import java.io.IOException;
import java.util.HashSet;

import basenetword.jack.com.network.okhttp.constant.SPKeys;
import basenetword.jack.com.network.okhttp.utils.SPUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xiaoyao on 2017/5/11.
 * <p>
 * 请求头里边添加cookie
 */

public class AddCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet<String>) SPUtils.get(SPKeys.COOKIE, new HashSet<String>());
        if (preferences != null) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
                Log.v("RxHttpUtils", "Adding Header Cookie--->: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            }
        }

        return chain.proceed(builder.build());
    }
}
