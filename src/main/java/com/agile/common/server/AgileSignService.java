package com.agile.common.server;

import com.agile.common.base.RETURN;
import org.springframework.stereotype.Service;

/**
 * 登陆服务
 * Created by mydeathtrial on 2017/3/2
 */
@Service
public class AgileSignService extends AgileMainService {
    public RETURN successSignOut(){
        return RETURN.LOGIN_OUT;
    }
    public RETURN notLogged(){
        return RETURN.NO_LOGIN;
    }
    public RETURN invalidSession(){
        return RETURN.INVALID_SESSION;
    }
    public RETURN homepage(){
        return RETURN.SUCCESS;
    }
    public RETURN errorLogging(){
        return RETURN.ERROR_LOGIN;
    }
}
