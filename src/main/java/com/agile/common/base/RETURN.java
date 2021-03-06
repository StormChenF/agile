package com.agile.common.base;

/**
 * Created by 佟盟 on 2017/1/9
 */
public final class RETURN {
    //默认响应状态码
    private String code = "00000";

    //默认响应信息
    private String msg = "操作执行成功";

    //请求类响应状态
    public static RETURN SUCCESS = new RETURN("000000","服务执行成功！");
    public static RETURN NO_MODULE = new RETURN("000001","请求模块不存在！");
    public static RETURN NO_SERVICE = new RETURN("000002","请求服务不存在！");
    public static RETURN NO_METHOD = new RETURN("000003","请求方法不存在！");
    public static RETURN NO_COMPLETE = new RETURN("000004","非法请求！");

    //登陆类响应状态
    public static RETURN NO_SIGN_IN = new RETURN("100000","账号尚未登陆！");
    public static RETURN SUCCESS_SIGN_OUT = new RETURN("100001","账号成功登出！");
    public static RETURN ERROR_SIGN_IN = new RETURN("100002","账号或密码验证失败！");
    public static RETURN INVALID_SESSION = new RETURN("100003","会话超时，请重新登陆！");
    public static RETURN ACCESS_DENIED = new RETURN("100004","权限不足，无法访问！");

    //运行错误响应状态
    static RETURN EXPRESSION = new RETURN("200000","程序错误！");
    static RETURN SQL_EXPRESSION = new RETURN("200001","SQL错误！");
    static RETURN HIBERNATE_EXPRESSION = new RETURN("200002","HIBERNATE错误！");
    static RETURN DATETIME_EXPRESSION = new RETURN("200003","时间错误！");
    static RETURN NULL_POINTER_EXPRESSION = new RETURN("200004","空指针错误！");
    static RETURN PARSE_EXPRESSION = new RETURN("200005","转换错误！");
    static RETURN IO_EXPRESSION = new RETURN("200006","IO流错误！");
    static RETURN TIMEOUT_EXPRESSION = new RETURN("200007","IO流错误！");
    static RETURN PARAMETER_EXPRESSION = new RETURN("200008","参数错误！");
    static RETURN IIIEGAL_ACCESS_EXPRESSION = new RETURN("200009","非法访问错误！");
    static RETURN IIIEGAL_ARGUMENT_EXPRESSION = new RETURN("200010","非法参数错误！");
    static RETURN INVOCATION_TARGET_EXPRESSION = new RETURN("200011","调用目标错误！");
    static RETURN SECURITY_EXPRESSION = new RETURN("200013","安全错误！");
    static RETURN CLASS_CAST_EXPRESSION = new RETURN("200014","对象强制转换错误！");
    static RETURN BEAN_EXPRESSION = new RETURN("200015","BEAN错误！");

    //业务逻辑响应状态
    public static RETURN PARAMETER_ERROR = new RETURN("300000","请求参数中，未包含可用参数！");

    public RETURN(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
