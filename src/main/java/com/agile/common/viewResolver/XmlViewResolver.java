package com.agile.common.viewResolver;

import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.Locale;

/**
 * Created by 佟盟 on 2017/8/1
 */
@Component
public class XmlViewResolver extends AbstractCachingViewResolver {

    @Override
    protected View loadView(String s, Locale locale) {
        MarshallingView view = new MarshallingView();
        view.setMarshaller(new XStreamMarshaller());
        return view;
    }
}
