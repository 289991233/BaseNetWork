package basenetword.jack.com.network.http.rxhttp;


import java.io.File;
import java.util.concurrent.TimeUnit;

import basenetword.jack.com.network.utils.Utils;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * 2017/3/16.
 */

public class OkRetrofit {
    //单例
    private static OkRetrofit sOkRetrofit;

    //Retrofit 对象
    private Retrofit mRetrofit;

    /**
     * 单例模式  获取实例
     * Double Check Lock(DCL)实现单例
     *
     * @return
     */
    public static OkRetrofit getInstance() {
        if (sOkRetrofit == null) {
            synchronized (OkRetrofit.class) {
                if (sOkRetrofit == null) {
                    sOkRetrofit = new OkRetrofit();
                }
            }
        }
        return sOkRetrofit;
    }

    public OkRetrofit() {
//        initView(Constant.APP_BASE_URL);
    }

    public OkRetrofit(String baseurl) {
        initView(baseurl);
    }

    private void initView(String url) {
        /**
         * 在这里直接设置连接超时.读取超时，写入超时
         */
        File file = new File(Utils.getContext().getApplicationContext().getCacheDir(), "rxCache");
        //缓存大小100M
        int cacheSize = 100 * 1024 * 1024;
        Cache cache = new Cache(file, cacheSize);
        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(new AddPubicParamsInterceptor())//自定义的拦截器，用于添加公共参数
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))//拦截器，用于日志的打印
                .cache(cache)
                .connectTimeout(15, TimeUnit.SECONDS)//连接
                .readTimeout(15, TimeUnit.SECONDS)//读
                .writeTimeout(15, TimeUnit.SECONDS)//写
                .addInterceptor(new NoNetInterceptor())
                .addNetworkInterceptor(new NetInterceptor())
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())//支持json  String类型
                .addConverterFactory(GsonConverterFactory.create())//默认直接转化为实体类，不会进行解密等处理
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//Retrofit2与Rxjava2之间结合的适配器
                .build();
    }

//    /**
//     * 公共api接口
//     *
//     * @return
//     */
//    public ApiServer getApi() {
//        return mRetrofit.create(ApiServer.class);
//    }

    /**
     * 自定义API接口
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getApis(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

}
