package com.agile.mvc.service.system;

import com.agile.constant.RETURN;
import com.agile.mvc.service.AbstractBusiness;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by tongmeng on 2017/1/9.
 */
@Service("SystemService")
public class SystemService extends AbstractBusiness {
    public RETURN homepage() throws Exception {
        return RETURN.SUCCESS;
    }

    public RETURN unLogin() throws Exception {
        return RETURN.NO_LOGIN;
    }
}
