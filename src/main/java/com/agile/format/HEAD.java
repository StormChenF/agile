package com.agile.format;

import com.agile.constant.RETURN;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by tongmeng on 2017/1/9.
 */
public class HEAD {
    private String url = "";
    private String ip = "";
    private String code = "";
    private String msg = "";

    public HEAD(RETURN returnState, HttpServletRequest request) {
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
