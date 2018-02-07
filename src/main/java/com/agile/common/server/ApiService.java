package com.agile.common.server;

import com.agile.common.base.Constant;
import com.agile.common.base.RETURN;
import com.agile.common.util.ObjectUtil;
import org.springframework.stereotype.Service;

/**
 * Created by 佟盟 on 2018/2/7
 */
@Service
public class ApiService extends MainService{

    public RETURN getApiInfo(){
        if(ObjectUtil.isEmpty(Constant.apiInfo))return RETURN.EXPRESSION;
        setOutParam("apiInfo",Constant.apiInfo);
        return RETURN.SUCCESS;
    }
}
