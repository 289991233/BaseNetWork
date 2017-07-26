package basenetword.jack.com.network.okhttp.base;

/**
 * Created by allen on 2017/6/23.
 * <p>
 * 请求结果基类   所有请求结果继承此类
 */

public class BaseResponse {

    /**
     * 错误码
     */
    private int code;

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * 错误描述
     */
    private String hint;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", hint='" + hint + '\'' +
                '}';
    }

}
