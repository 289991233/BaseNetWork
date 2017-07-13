package basenetword.jack.com.network.http.rxhttp;


import android.util.Log;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import basenetword.jack.com.network.http.Constants.Constants;
import basenetword.jack.com.network.http.SystemUtil;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.HttpException;

/**
 * Observer，使用此方式，需要进行订阅管理
 * 2017/4/6.
 */

public class BaseFlowableObserver<T> extends DisposableSubscriber<T> {
    private static final String TAG = "BaseFlowableObserver";
    private RequestCallBack mRequestCallBack;

    public BaseFlowableObserver(RequestCallBack requestCallBack) {
        this.mRequestCallBack = requestCallBack;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!SystemUtil.isNetworkConnected()) {
            onError(new ApiException(Constants.CONNECT_EXCEPTION,  ""));
        }
    }

    @Override
    public void onNext(T t) {
        try {
            mRequestCallBack.onSuccess(t,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable t) {
        String errorTips = "";
        try {
            if (t instanceof SocketTimeoutException) {
                Log.d(TAG, "onError: SocketTimeoutException----" + t.getMessage());
                errorTips = Constants.SOCKET_TIMEOUT_EXCEPTION;
                showHandleError(errorTips, t);
            } else if (t instanceof ConnectException) {
                Log.d(TAG, "onError: ConnectException-----" + t.getMessage());
                errorTips = Constants.CONNECT_EXCEPTION;
                showHandleError(errorTips, t);
            } else if (t instanceof UnknownHostException) {
                Log.d(TAG, "onError: UnknownHostException-----" + t.getMessage());
                errorTips = Constants.UNKNOWN_HOST_EXCEPTION;
                onShowError(errorTips);
            } else if (t instanceof HttpException) {
                Log.d(TAG, "onError: HttpException-----" + t.getMessage());
                errorTips = Constants.HTTP_EXCEPTION;
                onShowError(errorTips);
            } else if (t instanceof ApiException) {
                Log.d(TAG, "onError: ApiException-----" + t.getMessage());
                onShowError(t.getMessage());
//                mRequestCallBack.onError((ApiException) t);
            } else if (t instanceof NullPointerException) {
                Log.d(TAG, "onError:NullPointerException----" + t.getMessage());
                errorTips = Constants.NULL_POINT_EREXCEPTION;
            } else if (t instanceof RuntimeException) {
                Log.d(TAG, "onError: RuntimeException-----" + t.getMessage());
//                mRequestCallBack.onHandleApiException((ApiException) t);
            } else if (t instanceof JsonParseException
                    || t instanceof JSONException
                    || t instanceof ParseException) {
                Log.d(TAG, "onError: ParseException-----" + t.getMessage());
                errorTips = Constants.PARSE_EXCEPTION;
                onShowError(errorTips);
            } else if (t instanceof javax.net.ssl.SSLHandshakeException) {
                Log.d(TAG, "onError: SSLHandshakeException-----" + t.getMessage());
                errorTips = Constants.SSL_HANDSHAKE_EXCEPTION;
                onShowError(errorTips);
            } else {
                Log.d(TAG, "onError:----" + t.getClass().getName());
                Log.d(TAG, "onError:----" + t.getMessage());
                errorTips = Constants.OTHER_EXCEPTION;
                onShowError(errorTips);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
//                mRequestCallBack.onFinish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onComplete() {
//        mRequestCallBack.onFinish();
    }

    public void showHandleError(String errorTips, Throwable t) {
        onShowError(errorTips);
        mRequestCallBack.onError(t, errorTips);
    }


    //打印错误信息
    public void onShowError(String text) {
        Log.d("onShowError", text);
    }
}
