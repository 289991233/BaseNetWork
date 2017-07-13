package basenetword.jack.com.network.http.models;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 描    述：
 * 创 建 人：SDX
 * 创建日期：2017/6/19 16:10
 * 修订历史：
 * 修 改 人：
 */
public abstract class XBaseModel  implements BaseModel{
    //保存观察者和订阅者的订阅关系对象
    public CompositeDisposable compositeDisposable;

    /**
     * 使用Disposable 必须进行订阅管理
     *
     * @param disposable
     */
    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public void dispose() {
        //解除compositeDisposable中所有保存的Disposable对象中的订阅关系
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
    }
}
