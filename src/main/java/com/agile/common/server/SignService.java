package com.agile.common.server;

import com.agile.common.base.RETURN;
import com.agile.common.util.CacheUtil;
import org.springframework.stereotype.Service;

/**
 * 登陆服务
 * Created by mydeathtrial on 2017/3/2
 */
@Service
public class SignService extends MainService {
    public RETURN successSignOut(){
        return RETURN.SUCCESS_SIGN_OUT;
    }
    public RETURN notSignIn(){
        CacheUtil.setCache("asd","23");
        return RETURN.NO_SIGN_IN;
    }
    public RETURN invalidSession(){
        return RETURN.INVALID_SESSION;
    }
    public RETURN expiredSession(){
        return RETURN.EXPIRED_SESSION;
    }
    public RETURN homepage(){
        return RETURN.SUCCESS;
    }
    public RETURN errorSignIn(){
        return RETURN.ERROR_SIGN_IN;
    }
    public RETURN accessDenied(){
        return RETURN.ACCESS_DENIED;
    }
}
