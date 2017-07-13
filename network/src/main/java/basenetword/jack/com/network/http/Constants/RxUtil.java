package basenetword.jack.com.network.http.Constants;

import android.support.annotation.NonNull;
import android.util.Log;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import basenetword.jack.com.network.http.BaseView;
import basenetword.jack.com.network.http.HttpResult;
import basenetword.jack.com.network.http.rxhttp.ApiException;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava 2.X不再支持null值，如果传入一个null会抛出NullPointerException
 * Observable,Flowable 前者不支持背压，后者支持背压
 */

public class RxUtil {
    /**
     * 不支持背压
     * <p>
     * 公共处理，线程切换代码
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                //.retry(0) 错误重试机制，值为重试次数
                return upstream.retry(0)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 支持背压
     * <p>
     * 公共处理，线程切换代码
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> applyFlowableSchedulers() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                //.retry(0) 错误重试机制，值为重试次数
                return upstream.retry(0)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * 返回结果处理（compose）(Observable)
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<HttpResult<T>, T> handleHttpResultObservable() {
        return new ObservableTransformer<HttpResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<HttpResult<T>> upstream) {
                return upstream.concatMap(new Function<HttpResult<T>, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(HttpResult<T> tHttpResult) throws Exception {
                        if (tHttpResult.getCode().equals("40000")) {
                            Log.e("xxx",   tHttpResult.getCode()+"--成功");
                            return createDataObservable(tHttpResult.getList());
                        } else {
                            Log.e("xxx",  tHttpResult.getCode()+"--失败");
                            return Observable.error(new ApiException(tHttpResult.getHint(), tHttpResult.getCode()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 返回结果处理（compose）(Flowable)
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<HttpResult<T>, T> handleHttpResultFlowable() {
        return new FlowableTransformer<HttpResult<T>, T>() {
            @Override
            public Publisher<T> apply(Flowable<HttpResult<T>> upstream) {
                return upstream.concatMap(new Function<HttpResult<T>, Publisher<? extends T>>() {
                    @Override
                    public Publisher<? extends T> apply(HttpResult<T> tHttpResult) throws Exception {
                        if (tHttpResult.getCode().equals("40000")) {
                            return createDataFlowable(tHttpResult.getList());
                        } else {
                            return Flowable.error(new ApiException(tHttpResult.getHint(), tHttpResult.getCode()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 返回结果处理（compose）(Observable)---只返回json中的msg数据
     *
     * @return
     */
    public static ObservableTransformer<HttpResult<Object>, String> handleHttpResultMessage() {
        return new ObservableTransformer<HttpResult<Object>, String>() {
            @Override
            public ObservableSource<String> apply(Observable<HttpResult<Object>> upstream) {
                return upstream.concatMap(new Function<HttpResult<Object>, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(HttpResult<Object> tHttpResult) throws Exception {
                        if (tHttpResult.getCode().equals("40000")) {
                            return createDataObservable(tHttpResult.getHint());
                        } else {
                            return Observable.error(new ApiException(tHttpResult.getHint(), tHttpResult.getCode()));
                        }
                    }
                });
            }
        };

    }

    /**
     * 返回结果处理（compose）(Flowable)---只返回json中的msg数据
     *
     * @return
     */
    public static FlowableTransformer<HttpResult<Object>, String> handleHttpResultMessageFlowable() {
        return new FlowableTransformer<HttpResult<Object>, String>() {
            @Override
            public Publisher<String> apply(Flowable<HttpResult<Object>> upstream) {
                return upstream.concatMap(new Function<HttpResult<Object>, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(HttpResult<Object> tHttpResult) throws Exception {
                        if (tHttpResult.getCode().equals("40000")) {
                            return createDataFlowable(tHttpResult.getHint());
                        } else {
                            return Flowable.error(new ApiException(tHttpResult.getHint(), tHttpResult.getCode()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Observable
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableSource<T> createDataObservable(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> o) throws Exception {
                if (t != null) {
                    o.onNext(t);
                    o.onComplete();
                } else {
                    Log.e("Result", " t is null");
                    o.onError(new ApiException("", ""));
                }
            }
        });
    }

    /**
     * 生成 Flowable
     * 默认采用BackpressureStrategy.LATEST
     *
     * @param <T>
     * @return
     */
    public static <T> Publisher<T> createDataFlowable(final T t) {
        return createDataFlowable(t, BackpressureStrategy.LATEST);
    }

    /**
     * 生成 Flowable
     *
     * @param t
     * @param mode
     * @param <T>
     * @return
     */
    public static <T> Publisher<T> createDataFlowable(final T t, BackpressureStrategy mode) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> s) throws Exception {
                if (t != null) {
                    s.onNext(t);
                    s.onComplete();
                } else {
                    s.onError(new ApiException("", ""));
                }
            }
        }, mode);
    }
    //----------------------------------------------------rxJava控制进度条--------------------

    /**
     * 指定开始和结束时显示进度条的Observable（compose）(Observable)
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> progressDialogObservable(final BaseView view, final String text, final boolean cancelable) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (view != null)
                            view.showDialog();
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (view != null)
                            view.hideDialog();
                    }
                });
            }
        };
    }


    /**
     * 指定开始和结束时显示进度条的Flowable（compose）(Flowable)
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> progressDialogFlowable(final BaseView view, final String text, final boolean cancelable) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        if (view != null)
                            view.showDialog();
                    }
                }).doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (view != null)
                            view.hideDialog();
                    }
                });
            }
        };
    }


    /**
     * 指定开始和结束时显示进度条的Observable（compose）(Observable)
     *
     * @param view
     * @param text
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> progressDialogObservable(final BaseView view, final String text) {
        return progressDialogObservable(view, text, true);
    }

    /**
     * 指定开始和结束时显示进度条的Flowable（compose）(Flowable)
     *
     * @param view
     * @param text
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> progressDialogFlowable(final BaseView view, final String text) {
        return progressDialogFlowable(view, text, true);
    }
    //----------------------------------------------------rxJava控制进度条--------------------
}
