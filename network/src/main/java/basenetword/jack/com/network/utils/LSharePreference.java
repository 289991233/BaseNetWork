package basenetword.jack.com.network.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * Created by admin on 2015/9/6.
 * 封装sharePreference
 */
public class LSharePreference {
    //    private static String SP_NAME = Utils.getContext().getPackageName();
    private static String SP_NAME = "ssssss";


    private SharedPreferences sp;
    private Editor editor;
    private static LSharePreference instance;

    public static synchronized LSharePreference getInstance(Context context) {
        if (instance == null) {
            instance = new LSharePreference(context);
        }

        return instance;
    }

    private LSharePreference(Context context) {
        this.init(context);
    }

    private void init(Context context) {
        if (context != null) {
            this.sp = context.getSharedPreferences(SP_NAME, 0);
            this.editor = this.sp.edit();
        }
    }


    public void setString(String key, String value) {
        this.editor.putString(key, value);
        this.editor.apply();
    }

    public String getString(String key) {
        return this.getString(key, (String) null);
    }

    public String getString(String key, String defValue) {
        return this.sp.getString(key, defValue);
    }

    public void setInt(String key, int value) {
        this.editor.putInt(key, value);
        this.editor.apply();
    }

    public int getInt(String key) {
        return this.getInt(key, 0);
    }

    public int getInt(String key, int defValue) {
        return this.sp.getInt(key, defValue);
    }

    public void setFloat(String key, float value) {
        this.editor.putFloat(key, value);
        this.editor.apply();
    }

    public float getFloat(String key) {
        return this.getFloat(key, 0.0F);
    }

    public float getFloat(String key, float defValue) {
        return this.sp.getFloat(key, defValue);
    }

    public void setBoolean(String key, boolean value) {
        this.editor.putBoolean(key, value);
        this.editor.apply();
    }

    public boolean getBoolean(String key) {
        return this.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return this.sp.getBoolean(key, defValue);
    }

    public void delContent(String key) {
        this.editor.remove(key);
        this.editor.apply();
    }
}
