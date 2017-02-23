package com.agile.common.base;

/**
 * Created by tongmeng on 2017/1/9.
 */
public final class RETURN {
    //响应状态码
    private String code = "00000";
    //响应信息
    private String msg = "操作执行成功";

    public static RETURN SUCCESS = new RETURN("000000","服务执行成功！");
    public static RETURN NO_MODULE = new RETURN("000001","请求模块不存在！");
    public static RETURN NO_SERVICE = new RETURN("000002","请求服务不存在！");
    public static RETURN NO_METHOD = new RETURN("000003","请求方法不存在！");

    public static RETURN NO_LOGIN = new RETURN("100000","账号尚未登陆！");
    public static RETURN LOGIN_OUT = new RETURN("100001","账号成功登出！");
    public static RETURN ERROR_LOGIN = new RETURN("100002","账号成功登出！");
    public static RETURN INVALID_SESSION = new RETURN("100003","账号成功登出！");

    public static RETURN EXPRESSION = new RETURN("200000","程序错误！");
    public static RETURN SQL_EXPRESSION = new RETURN("200001","SQL错误！");
    public static RETURN HIBERNATE_EXPRESSION = new RETURN("200002","HIBERNATE错误！");
    public static RETURN DATETIME_EXPRESSION = new RETURN("200003","时间错误！");
    public static RETURN NULL_POINTER_EXPRESSION = new RETURN("200004","空指针错误！");
    public static RETURN PARSE_EXPRESSION = new RETURN("200005","转换错误！");
    public static RETURN IO_EXPRESSION = new RETURN("200006","IO流错误！");
    public static RETURN TIMEOUT_EXPRESSION = new RETURN("200007","IO流错误！");
    public static RETURN PARAMETER_EXPRESSION = new RETURN("200008","参数错误！");
    public static RETURN IIIEGAL_ACCESS_EXPRESSION = new RETURN("200009","非法访问错误！");
    public static RETURN IIIEGAL_ARGUMENT_EXPRESSION = new RETURN("200010","非法参数错误！");
    public static RETURN INVOCATION_TARGET_EXPRESSION = new RETURN("200011","调用目标错误！");
    public static RETURN NO_SUCH_METHOD_EXPRESSION = new RETURN("200012","没有这样的方法错误！");
    public static RETURN SECURITY_EXPRESSION = new RETURN("200013","安全错误！");
    public static RETURN CLASS_CAST_EXPRESSION = new RETURN("200014","对象强制转换错误！");
    public static RETURN BEAN_EXPRESSION = new RETURN("200015","BEAN错误！");

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
