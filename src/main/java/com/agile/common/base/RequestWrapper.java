package com.agile.common.base;

import org.springframework.validation.BeanPropertyBindingResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Iterator;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 * Created by 佟盟 on 2018/3/26
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private Map<String , Object> params;

    public RequestWrapper(HttpServletRequest request , Map<String , Object> extendParams) {
        super(request);
        extendParams.remove("service");
        extendParams.remove("method");

        Iterator entries = extendParams.entrySet().iterator();

        while (entries.hasNext()) {

            Map.Entry entry = (Map.Entry) entries.next();

            Object value = entry.getValue();

            if(value instanceof Page || value instanceof BeanPropertyBindingResult){
                entries.remove();
            }
        }
        params = extendParams;
    }

    public Map<String , Object> getForwardParameterMap() {
        return params;
    }
}
