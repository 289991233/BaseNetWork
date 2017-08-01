package basenetword.jack.com.network.test;

import basenetword.jack.com.network.test.homefragment.TWelcomeEntity;
import basenetword.jack.com.network.test.mvptest.HomeNewEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/7/13 14:15
 * 修订历史：
 * 修 改 人：
 */
public interface TApiServer {
    @GET("apps/index/index")
    Observable<String> getTestData1(@Query("page") String page);

    @GET("apps/index/index")
    Observable<HomeNewEntity> getTestData2(@Query("page") String page);

    @GET("apps/start/index")
    Observable<TWelcomeEntity> getStartData();

    @GET("apps/start/index")
    Observable<String> getStartData1();
}

