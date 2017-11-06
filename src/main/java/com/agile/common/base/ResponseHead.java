package com.agile.common.base;

import com.agile.common.util.ServletUtil;
import java.io.Serializable;

/**
 * Created by 佟盟 on 2017/1/9
 */
public class ResponseHead implements Serializable {
    private static final long serialVersionUID = 97555324631150979L;
    private static String ip = ServletUtil.getLocalIP();
    private String code;
    private String msg;

    public ResponseHead(RETURN returnState) {
        this.code = returnState.getCode();
        this.msg = returnState.getMsg();
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