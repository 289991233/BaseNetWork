

package basenetword.jack.com.network.http;


import android.util.Log;

import basenetword.jack.com.network.http.models.TBaseModel;

public class TBasePresenter<T extends TContract.View, E extends TContract.Model> extends TBaseModel {
    protected T view;
    protected E model;

    public void init(Object view, Object model) {
        this.view = (T) view;
        this.model = (E) model;
    }
//    public TBasePresenter(T view, E model) {
//        this.view = view;
//        this.model = model;
//    }

    public void start() {

    }

    public void end() {
        Log.e("xx", view + "----" + model + "----销毁了");
        view = null;
        model = null;
        dispose();
    }


}
