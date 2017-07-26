package basenetword.jack.com.network.okhttp.upload;



import java.io.File;

import basenetword.jack.com.network.okhttp.http.RetrofitClient;
import basenetword.jack.com.network.okhttp.interceptor.Transformer;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by allen on 2017/6/14.
 * <p>
 * 为上传单独建一个retrofit
 */

public class UploadRetrofit {

    private static UploadRetrofit instance;
    private Retrofit mRetrofit;

    private static String baseUrl = "https://api.github.com/";


    public UploadRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public static UploadRetrofit getInstance() {

        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new UploadRetrofit();
                }
            }

        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public static Observable<ResponseBody> uploadImg(String uploadUrl, String filePath) {
        File file = new File(filePath);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestFile);

        return UploadRetrofit
                .getInstance()
                .getRetrofit()
                .create(UploadFileApi.class)
                .uploadImg(uploadUrl, body)
                .compose(Transformer.<ResponseBody>switchSchedulers());
    }
}
