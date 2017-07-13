package basenetword.jack.com.network.http.rxhttp;

import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import basenetword.jack.com.network.BuildConfig;
import basenetword.jack.com.network.http.SecurityUtil;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 *  2017/3/16.
 */

public class CommonInterceptor implements Interceptor {
    public final static String TAG = "CommonInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();

        //文件上传 不需要封装公共参数【应公司而异，本公司图片为单独的服务器，只需要上传图片，后接受图片服务器的路径进行拼接url显示图片即可】
//        if (request.body().contentType() != null && "form-data".equals(request.body().contentType().subtype())) {
//            injectParamsIntoUrl(request, requestBuilder, new HashMap<String, String>());
//        } else {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        Map<String, String> paramsMap = new HashMap<>();
        filterParams(paramsMap);
        if (!paramsMap.isEmpty()) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                formBodyBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody formBody = formBodyBuilder.build();
        String postBodyString = bodyToString(request.body());
        postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
        //url签名
//            postBodyString = setupParams(postBodyString);
        if (BuildConfig.DEBUG)
            Log.d("OkHttp", "--> " + request.url().toString() + "?" + postBodyString);
        requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString));
//        }
        request = requestBuilder.build();
        return chain.proceed(request);
    }

    private String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    /**
     * 公共参数封装
     ***/
    private Map<String, String> filterParams(Map<String, String> params) {
//        params.put("appId", ParamsConfig.appId);
//        params.put("phoneType", ParamsConfig.phoneType);
//        params.put("osVersion", ParamsConfig.osVersion);
//        params.put("sysVersion", ParamsConfig.sysVersion);
//        params.put("apiVersion", ParamsConfig.apiVersion);
//        params.put("appVersion", ParamsConfig.appVersion);
//        if (!TextUtils.isEmpty(ParamsConfig.getClientId()))
//            params.put("clientId", ParamsConfig.getClientId());
//        UserEntity user = CacheModel.getInstance().getUser();
//        if (user != null) {
//            if (!TextUtils.isEmpty(user.getTokenid()))
//                params.put("tokenId", user.getTokenid());
//            if (!TextUtils.isEmpty(user.getUserId()))
//                params.put("userId", user.getUserId());
//        }
        return params;
    }

    /**
     * 数据签名工具类
     *
     * @param content
     * @return
     * @throws UnsupportedEncodingException
     */
    private String setupParams(String content) throws UnsupportedEncodingException {
        String sign = SecurityUtil.getSign(URLDecoder.decode(content, "utf-8"));
        return content.concat("&").concat("sign=").concat(sign);
    }

    private void injectParamsIntoUrl(Request request, Request.Builder requestBuilder, Map<String, String> paramsMap) {
        HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
        if (paramsMap.size() > 0) {
            Iterator iterator = paramsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                httpUrlBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }
        requestBuilder.url(httpUrlBuilder.build());
    }
}
