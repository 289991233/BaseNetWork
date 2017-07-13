

package basenetword.jack.com.network.http;


import android.util.Log;

public class XBasePresenter<T extends XContract.View, E extends XContract.Model> {
    protected T view;
    protected E model;

    public void init(Object view, Object model) {
        this.view = (T) view;
        this.model = (E) model;
    }
//    public XBasePresenter(T view, E model) {
//        this.view = view;
//        this.model = model;
//    }

    public void start() {

    }

    public void end() {
        Log.e("xx", view + "----" + model + "----销毁了");
        view = null;
        model = null;
    }


}