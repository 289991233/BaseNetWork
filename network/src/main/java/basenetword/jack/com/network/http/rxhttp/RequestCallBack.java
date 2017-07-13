package basenetword.jack.com.network.http.rxhttp;

/**
 * 描    述：请求回调
 * 创 建 人：SDX
 * 创建日期：2017/6/20 13:54
 * 修订历史：
 * 修 改 人：
 */
public interface RequestCallBack<T> {
    void onSuccess(T data, int type);

//    //服务器请求成功，code响应失败错误返回信息
//    void onHandleApiException(ApiException e);

    //结束所有异常和，异常的错误信息，可以通过taost进行显示
    void onError(Throwable t, String errorTips);
}
