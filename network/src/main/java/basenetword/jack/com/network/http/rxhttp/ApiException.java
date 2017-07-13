package basenetword.jack.com.network.http.rxhttp;


public class ApiException extends Exception{
    private String code;
    public ApiException(String error, String code){
        super(error);
        this.code = code;
    }

    public ApiException(String error){
        super(error);
        this.code = "";
    }
    public String getCode() {
        return code;
    }

}
