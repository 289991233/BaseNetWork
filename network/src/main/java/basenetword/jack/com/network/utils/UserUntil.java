package basenetword.jack.com.network.utils;

import android.content.Context;

/**
 * 描    述： 保存相关ToKen
 * 创 建 人：SDX
 * 创建日期：2017/7/10 10:32
 * 修订历史：
 * 修 改 人：
 */
public class UserUntil {
    public static boolean isLogin(Context context) {
        if (!LSharePreference.getInstance(context).getBoolean("login")) {
//            CommonUntil.StartActivity(context, ActivityLogin.class);
        }
        return LSharePreference.getInstance(context).getBoolean("login");
    }

    public static boolean Login(Context context) {
        LSharePreference.getInstance(context).setBoolean("login", true);
        return LSharePreference.getInstance(context).getBoolean("login");
    }

    public static boolean getLogin(Context context) {
        return LSharePreference.getInstance(context).getBoolean("login");
    }

    /**
     * 退出登录
     *
     * @param context
     * @return
     */
    public static boolean OutLogin(Context context) {
        LSharePreference.getInstance(context).setBoolean("login", false);
        cleaAllInfo(context);
        return LSharePreference.getInstance(context).getBoolean("login");
    }

    /**
     * 获取用户名
     *
     * @param context
     * @return
     */
    public static String getName(Context context) {
        return LSharePreference.getInstance(context).getString("name", "");
    }

    /**
     * 设置用户名
     *
     * @param context
     * @param name
     */
    public static void setName(Context context, String name) {
        LSharePreference.getInstance(context).setString("name", name);
    }

    /**
     * 获取用户号码
     *
     * @param context
     * @return
     */
    public static String getPhone(Context context) {
        return LSharePreference.getInstance(context).getString("Phone", "");
    }

    /**
     * 设置用户号码
     *
     * @param context
     * @param Phone
     */
    public static void setPhone(Context context, String Phone) {
        LSharePreference.getInstance(context).setString("Phone", Phone);
    }

    /**
     * 获取用户头像
     *
     * @param context
     * @return
     */
    public static String getHead(Context context) {
        return LSharePreference.getInstance(context).getString("head", "");
    }

    /**
     * 设置用户头像
     *
     * @param context
     * @param head
     */
    public static void setHead(Context context, String head) {
        LSharePreference.getInstance(context).setString("head", head);
    }

    /**
     * 获取Token
     *
     * @param context
     * @return
     */
    public static String getToken(Context context) {
        return LSharePreference.getInstance(context).getString("token", "");
    }

//    public static String getTestToken() {
//        return "da7fc2fdc42ba1b694859a91672497c4";
//    }

    /**
     * 设置Token
     *
     * @param context
     * @param code
     */
    public static void setToken(Context context, String code) {
        LSharePreference.getInstance(context).setString("token", code);
    }

    /**
     * 获取融云Token
     *
     * @param context
     * @return
     */
    public static String getRToken(Context context) {
        return LSharePreference.getInstance(context).getString("rtoken", "");
    }

    /**
     * 设置融云Token
     *
     * @param context
     * @param code
     */
    public static void setRToken(Context context, String code) {
        LSharePreference.getInstance(context).setString("rtoken", code);
    }

    /**
     * 获取融云ID
     *
     * @param context
     * @return
     */
    public static String getRid(Context context) {
        return LSharePreference.getInstance(context).getString("rid", "");
    }

    /**
     * 设置融云ID
     *
     * @param context
     * @param code
     */
    public static void setRid(Context context, String code) {
        LSharePreference.getInstance(context).setString("rid", code);
    }

    /**
     * @param context
     * @return
     */
    public static String getGuide(Context context) {
        return LSharePreference.getInstance(context).getString("guide", "");
    }

    /**
     * 记录引导页
     *
     * @param context
     * @param
     */
    public static void setGuide(Context context, String gudie) {
        LSharePreference.getInstance(context).setString("guide", gudie);
    }


    /**
     * 我的界面
     *
     * @param context
     * @return
     */
    public static String getGuideMy(Context context) {
        return LSharePreference.getInstance(context).getString("guidemy", "");
    }

    /**
     * 记录引导页我的界面
     *
     * @param context
     * @param
     */
    public static void setGuideMy(Context context, String gudie) {
        LSharePreference.getInstance(context).setString("guidemy", gudie);
    }


    /**
     * 商家主页
     *
     * @param context
     * @return
     */
    public static String getGuideShop(Context context) {
        return LSharePreference.getInstance(context).getString("guideshop", "");
    }

    /**
     * 记录引导页商家主页
     *
     * @param context
     * @param
     */
    public static void setGuideShop(Context context, String gudie) {
        LSharePreference.getInstance(context).setString("guideshop", gudie);
    }


    /**
     * 发布产品
     *
     * @param context
     * @return
     */
    public static String getGuideAdd(Context context) {
        return LSharePreference.getInstance(context).getString("guideadd", "");
    }

    /**
     * 记录引导页发布产品
     *
     * @param context
     * @param
     */
    public static void setGuideAdd(Context context, String gudie) {
        LSharePreference.getInstance(context).setString("guideadd", gudie);
    }

    public static void cleaAllInfo(Context context) {
        LSharePreference.getInstance(context).setInt("id", 0);
        LSharePreference.getInstance(context).setString("token", "");
        LSharePreference.getInstance(context).setString("head", "");
        LSharePreference.getInstance(context).setString("Phone", "");
        LSharePreference.getInstance(context).setString("name", "");
        LSharePreference.getInstance(context).setString("rtoken", "");
        LSharePreference.getInstance(context).setString("rid", "");
    }


}
