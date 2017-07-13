package basenetword.jack.com.network.http.rxhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import basenetword.jack.com.network.utils.Loger;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkHttp {
    /**
     * 静态实例
     */
    private static OkHttp sOkHttpManager;

    /**
     * okhttpclient实例
     */
    private OkHttpClient mClient;

    /**
     * 因为我们请求数据一般都是子线程中请求，在这里我们使用了handler
     */
    private Handler mHandler;
    private Context mContext;
    public static   String mBaseUrl = "https://hssc.m.huisou.com/";

    /**
     * 必须在全局Application先调用，获取context上下文，
     */
    public static void init(Context app) {
        getInstance().inits(app);

    }

    private void inits(Context app) {
        mContext = app.getApplicationContext();
    }

    /**
     * 构造方法
     */
    private OkHttp() {
        // 可以通过实现 Logger 接口更改日志保存位置
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mClient = new OkHttpClient();

        OkHttpClient.Builder builder = mClient.newBuilder();
        builder.connectTimeout(15, TimeUnit.SECONDS);//连接
        builder.readTimeout(15, TimeUnit.SECONDS);//读
        builder.writeTimeout(15, TimeUnit.SECONDS);//写
//        builder.addInterceptor(loggingInterceptor);
        mClient = builder.build();

        /**
         * 初始化handler
         */
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 单例模式  获取OkHttp实例
     * Double Check Lock(DCL)实现单例
     *
     * @return
     */
    public static OkHttp getInstance() {
        if (sOkHttpManager == null) {
            synchronized (OkHttp.class) {
                if (sOkHttpManager == null) {
                    sOkHttpManager = new OkHttp();
                }
            }
        }
        return sOkHttpManager;
    }


    //-------------------------项目中 Get请求--------------------------
    public static Disposable GetRequset(String url, int type, IHttpRequest callBack) {
        getInstance().initGet(callBack, url, type);
        return null;
    }

    /**
     * 内部逻辑请求的方法
     *
     * @param callBack
     * @param url
     * @param type     如果大于10086则使用
     */
    private void initGet(final IHttpRequest callBack, String url, final int type) {
        String Url = (type >= 10086) ? url : mBaseUrl + url;
        final Request request = new Request.Builder().url(Url).build();
        final long startTime = System.currentTimeMillis();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    deliverDataFailure(request, e, callBack);
                }
                Loger.e("   \n");
                Loger.e("---------------------Request Log Start---------------------");
                Loger.e("| " + request.toString());
                Loger.e("| Response:" + result);
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                Loger.e("---------------------Request Log End:" + duration + "毫秒---------------------");
                Loger.e("   \n");
                deliverDataSuccess(callBack, result, type);
            }
        });
    }

    /**
     * 失败的时候调用
     *
     * @param request
     * @param e
     * @param callBack
     */
    private void deliverDataFailure(final Request request, final IOException e, final IHttpRequest callBack) {
        /**
         * 在这里使用异步处理
         */
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.requestFailure(request, e);
                }
            }
        });
    }

    /**
     * 成功的时候调用
     *
     * @param result
     * @param callBack
     */
    private void deliverDataSuccess(final IHttpRequest callBack, final String result, final int type) {
        /**
         * 在这里使用异步线程处理
         */
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        callBack.responseSucceed(type, result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    //    /**
//     * 数据回调接口
//     */
    public interface IHttpRequest {
        void responseSucceed(int type, String s) throws Exception;

        void requestFailure(Request request, IOException e);
    }

    //-------------------------项目中 Post请求--------------------------

    public static void PostRequset(String url, FormBody.Builder builder, int type, IHttpRequest callBack) {
        getInstance().initPost(callBack, url, builder, type);
    }


    private void initPost(final IHttpRequest callBack, final String url, FormBody.Builder builder, final int type) {
        String Url = (type >= 10086) ? url : mBaseUrl + url;

        /**
         * 如果是3.0之前版本的
         */
//        FormEncodingBuilder builder = new FormEncodingBuilder();

        /**
         * 3.0之后版本
         */
        //FormBody.Builder builder = new FormBody.Builder();

        RequestBody requestBody = builder.build();
        //结果返回
        // 请求对象
        final Request request = new Request.Builder().url(Url).post(requestBody).build();
        final long startTime = System.currentTimeMillis();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Loger.e("   \n");
                Loger.e("---------------------Request Log Start---------------------");
                Loger.e("| " + request.toString());
                StringBuilder sb = new StringBuilder();
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                Loger.e("| RequestParams:{" + sb.toString() + "}");
                Loger.e("| Response:" + result);
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                Loger.e("---------------------Request Log End:" + duration + "毫秒---------------------");
                Loger.e("   \n");
                deliverDataSuccess(callBack, result, type);
            }
        });
    }


}
