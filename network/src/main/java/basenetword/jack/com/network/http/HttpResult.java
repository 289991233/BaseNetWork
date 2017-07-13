package basenetword.jack.com.network.http;

/**
 */

public class HttpResult<T> {

    public static final String CODE_SUCCESSFUL = "40000";

    private String code;
    private String hint;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    private T list;




}
