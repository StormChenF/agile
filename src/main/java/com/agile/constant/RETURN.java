package com.agile.constant;

/**
 * Created by tongmeng on 2017/1/9.
 */
public final class RETURN {
    //响应状态码
    private String code = "00000";
    //响应信息
    private String msg = "操作执行成功";

    public static RETURN SUCCESS = new RETURN("000000","服务执行成功！");
    public static RETURN NO_SERVICE = new RETURN("100000","请求服务不存在！");
    public static RETURN NO_METHOD = new RETURN("200000","请求方法不存在！");
    public static RETURN NO_LOGIN = new RETURN("300000","账号尚未登陆！");

    public RETURN(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
