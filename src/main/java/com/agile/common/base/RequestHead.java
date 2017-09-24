package com.agile.common.base;

import com.agile.common.util.ServletUtil;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by 佟盟 on 2017/1/9
 */
public class RequestHead implements Serializable {
    private static final long serialVersionUID = 97555324631150979L;
    private String url;
    private String ip;
    private String code;
    private String msg;

    public RequestHead(RETURN returnState, HttpServletRequest request) {
        this.ip = ServletUtil.getCustomerIPAddr(request);
        this.url = request.getScheme() + "://" + ServletUtil.localhostFormat(request.getLocalAddr())+ ":" + request.getLocalPort() + request.getRequestURI();
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