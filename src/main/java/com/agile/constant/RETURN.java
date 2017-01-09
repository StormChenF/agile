package com.agile.constant;

/**
 * Created by tongmeng on 2017/1/9.
 */
public final class RETURN {
    //响应状态码
    private String errorCode = "00000";
    //响应信息
    private String errorMsg = "操作执行成功";

    public static RETURN SUCCESS = new RETURN("000000","服务执行成功！");
    public static RETURN NO_SERVICE = new RETURN("100000","请求服务不存在！");
    public static RETURN NO_METHOD = new RETURN("200000","请求方法不存在！");

    private RETURN(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public RETURN() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
