package com.agile.common.viewResolver;

import com.agile.common.view.JsonView;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * Created by 佟盟 on 2017/8/1
 */
@Component
public class JsonViewResolver implements ViewResolver {
    @Nullable
    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        return new JsonView();
    }
}
