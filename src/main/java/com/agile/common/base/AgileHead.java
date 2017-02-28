package com.agile.common.base;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tongmeng on 2017/1/9
 */
public class AgileHead {
    private String url = "";
    private String ip = "";
    private String code = "";
    private String msg = "";

    public AgileHead(RETURN returnState, HttpServletRequest request) {
        this.url = String.valueOf(request.getRequestURL());
        this.ip = request.getRemoteAddr();
        this.code = returnState.getCode();
        this.msg = returnState.getMsg();
    }

    public String getUrl() {
        return url;
    }

    public String getIp() {
        return ip;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}