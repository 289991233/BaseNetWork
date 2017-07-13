package basenetword.jack.com.network.test;

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
}

