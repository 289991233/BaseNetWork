
package basenetword.jack.com.network.http.Constants;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import basenetword.jack.com.network.http.XBasePresenter;
import basenetword.jack.com.network.http.XContract;


public class GenericHelper {

    public static <T> Class<T> getGenericClass(Class<?> klass, Class<?> filterClass) {
        Type type = klass.getGenericSuperclass();
        if(type == null || !(type instanceof ParameterizedType)) return null;
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        for (Type t : types) {
            if (isPresenter(t, filterClass)) {
                return (Class<T>) t;
            }
        }

        return null;
//        if(types == null || types.length == 0) return null;
//        return (Class<T>) types[0];
    }

    private static boolean isPresenter(Type t, Class<?> filterClass) {
        Class<?> aClass = (Class<?>) t;
        Class<?>[] classes = aClass.getInterfaces();
        for (Class<?> c : classes) {
            return c == filterClass || isPresenter(c, filterClass);
        }
        return false;
    }

    public static  <T> T newPresenter(Object obj) {
        if (!XContract.View.class.isInstance(obj)) {
            throw new RuntimeException("no implement XContract.BaseView");
        }
        try {
            Class<?> currentClass = obj.getClass();
//            Class<?> viewClass = getViewInterface(currentClass);
            Class<?> presenterClass = getGenericClass(currentClass, XContract.Presenter.class);
            Class<?> modelClass = getGenericClass(presenterClass, XContract.Model.class);
//            Constructor construct = presenterClass.getConstructor(viewClass, modelClass);
            XBasePresenter<?,?> xBasePresenter = (XBasePresenter<?, ?>) presenterClass.newInstance();
            xBasePresenter.init(obj, modelClass.newInstance());
            return (T) xBasePresenter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("instance of presenter fail\n" +
                " Remind presenter need to extends XBasePresenter");
    }


//    public static Class<?> getViewInterface(Class currentClass) {
//        Class<?>[] classes = currentClass.getInterfaces();
//        for (Class<?> c : classes) {
//            if (c != XContract.View.class) {
//                if (getViewInterface(c) == XContract.View.class) {
//                    return c;
//                }
//            }
//            return c;
//        }
//        throw new RuntimeException("no implement XContract.BaseView");
//    }
}