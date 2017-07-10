package com.jack.basenetword;

import android.app.Activity;
import android.text.TextUtils;

import com.jack.basenetword.activity.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import basenetword.jack.com.network.BaseApplication;
import basenetword.jack.com.network.http.OkHttp;
import basenetword.jack.com.network.utils.CrashHandler;
import basenetword.jack.com.network.utils.Loger;
import basenetword.jack.com.network.utils.Utils;


public class MyApplication extends BaseApplication {

    private static MyApplication instance;
    private static List<Activity> activityList = new ArrayList<>();
    public static boolean isLog = BuildConfig.DEBUG;

    public void onCreate() {
        super.onCreate();
        instance = this;
        Loger.setDebug(true);
        OkHttp.init(this);
        Utils.init(this);
        String processName = getProcessName();  //注意区分进程初始化不同的东西【避免其他无关进程不必要的初始化】
        if (!TextUtils.isEmpty(processName) && processName.equals(this.getPackageName())) { //main Process 当前app进程
            if (!BuildConfig.DEBUG) {
                CrashHandler crashHandler = CrashHandler.getInstance();
                crashHandler.init(getApplicationContext(), MainActivity.class);
            }
        } else {
            //其他进程 如推送等...
        }
    }


    /**
     * 获取进程名字
     *
     * @return
     */
    public String getProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static MyApplication getInstance() {
        return instance;
    }

    public static void addActivity(Activity activity) {
        if (activity != null) {
            activityList.add(activity);
        }
    }

    public static void getActivity(int index) {
        if (index > 0 && index < activityList.size() - 1) {
            activityList.get(index);
        }
    }

    public static Activity getCurrentActivity() {
        if (activityList != null && activityList.size() >= 1) {
            return activityList.get(activityList.size() - 1);
        } else {
            return null;
        }
    }

    public static Activity getActivity(Class clazz) {
        if (activityList != null && activityList.size() > 1) {
            for (int i = 0; i < activityList.size(); i++) {
                Activity activity = activityList.get(i);
                if (activity != null && (activity.getClass() == clazz)) {
                    return activity;
                }
            }
        }
        return null;
    }


    public static void removeActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activityList.remove(activity);
        }
    }

    public static void clearActivity() {
        for (Activity activity : activityList) {
            if (activity != null) {
                activity.finish();
            }
        }
        activityList.clear();
    }

    public static void clearOtherActivity(Activity activity0) {
        for (Activity activity : activityList) {
            if (activity != null && activity != activity0) {
                activity.finish();
            }
        }
        activityList.clear();
        activityList.add(activity0);
    }
}
