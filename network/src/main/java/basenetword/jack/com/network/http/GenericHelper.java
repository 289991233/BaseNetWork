
package basenetword.jack.com.network.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


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
        if (!TContract.View.class.isInstance(obj)) {
            throw new RuntimeException("no implement TContract.BaseView");
        }
        try {
            Class<?> currentClass = obj.getClass();
//            Class<?> viewClass = getViewInterface(currentClass);
            Class<?> presenterClass = getGenericClass(currentClass, TContract.Presenter.class);
            Class<?> modelClass = getGenericClass(presenterClass, TContract.Model.class);
//            Constructor construct = presenterClass.getConstructor(viewClass, modelClass);
            TBasePresenter<?,?> xBasePresenter = (TBasePresenter<?, ?>) presenterClass.newInstance();
            xBasePresenter.init(obj, modelClass.newInstance());
            return (T) xBasePresenter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("instance of presenter fail\n" +
                " Remind presenter need to extends TBasePresenter");
    }


//    public static Class<?> getViewInterface(Class currentClass) {
//        Class<?>[] classes = currentClass.getInterfaces();
//        for (Class<?> c : classes) {
//            if (c != TContract.View.class) {
//                if (getViewInterface(c) == TContract.View.class) {
//                    return c;
//                }
//            }
//            return c;
//        }
//        throw new RuntimeException("no implement TContract.BaseView");
//    }
}