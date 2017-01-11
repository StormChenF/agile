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
    public RETURN addDictionary() throws Exception {
        HashMap<String,Object> s = new HashMap<String, Object>();
        s.put("aa","bb");
        this.setOutParam(s);
        return RETURN.SUCCESS;
    }
}
