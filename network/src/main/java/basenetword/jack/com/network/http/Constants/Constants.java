package basenetword.jack.com.network.http.Constants;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import basenetword.jack.com.network.utils.ToastUtil;
import basenetword.jack.com.network.utils.Utils;


/**
 *  on 2017/6/5.
 */

public class Constants {
    public static final String PATH_DATA = Utils.getContext().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/networkCache";

    public static String SOCKET_TIMEOUT_EXCEPTION = "";
    public static String CONNECT_EXCEPTION = "";
    public static String UNKNOWN_HOST_EXCEPTION = "";
    public static String NULL_POINT_EREXCEPTION = "";
    public static String HTTP_EXCEPTION = "";
    public static String OTHER_EXCEPTION = "";
    public static String PARSE_EXCEPTION = "";
    public static String SSL_HANDSHAKE_EXCEPTION = "";


    public interface REQUEST_CODE {
        // take photo
        int TAKE_CAMERA = 0x001;
        int TAKE_CAMERA_WITH_CROP = 0x002;
        int TAKE_GALLERY = 0x003;
        int TAKE_GALLERY_WITH_CROP = 0x004;
        int TAKE_PHOTO_WITH_CROP = 0x005;
        int TAKE_FILE = 0x006;
        int TAKE_FILE_WITH_CROP = 0x007;
    }

    public interface INTENT_KEY {
        String URL = "url";
        String TITLE = "title";
    }

    /**
     * @param s
     * @return
     */
    public static String getJson(String s) {
        String json = "";
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.optString("code").equals("40000")) {
                json = s;
            } else if (jsonObject.optString("code").equals("40004")) {
                ToastUtil.getInstance().showToast(jsonObject.optString("hint").toString());
            } else {
                ToastUtil.getInstance().showToast(jsonObject.optString("hint").toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
