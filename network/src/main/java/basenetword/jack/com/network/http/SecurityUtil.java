package basenetword.jack.com.network.http;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import basenetword.jack.com.network.http.rxhttp.MD5Util;

/**
 * 数据签名工具类
 */
public class SecurityUtil {

    public static String getSign(Map<String, String>  paramMap) {
        Map<String, String[]> paramsArr = new HashMap();
        for(Map.Entry<String, String> entry:paramMap.entrySet()){
            String[] arr = {entry.getValue()};
            paramsArr.put(entry.getKey(),arr);
        }
        return executeSign(paramsArr);
    }

    public static String getSign(String  param) {
        Map<String, String[]> paramsArr = new HashMap();
        String[] arrys = param.split("&");

        for(String str:arrys){
            String[] arr = str.split("=");
            String[] value = {arr[1]};
            paramsArr.put(arr[0],value);
        }
        return executeSign(paramsArr);
    }


    public static String executeSign(Map<String, String[]>  params) {

        TreeSet<String> ts = new TreeSet<String>();
        ts.addAll(params.keySet());
        StringBuilder sb = new StringBuilder();
        for (String key : ts) {
            if ("sign".equals(key)) {
                continue;
            }
            sb.append(key + "=" +params.get(key)[0] + "&");
        }
        sb.setLength(sb.length() - 1);
        String sign = MD5Util.MD5Encode(String.valueOf(sb.toString()) + "carfree", "utf-8");
        return sign;
    }
}
