package com.agile.common.view;

import com.agile.common.base.Constant;
import com.agile.common.util.StringUtil;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.view.AbstractView;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by 佟盟 on 2018/3/26
 */
public class ForwardView extends AbstractView {
    private String url;

    public ForwardView(String url) {
        this.url = url;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //去掉重复传参
        Map<String, String[]> parameterMap = request.getParameterMap();
        model.remove("service");
        model.remove("method");
        for (Map.Entry<String, String[]> entity:parameterMap.entrySet()) {
            model.remove(entity.getKey());
        }

        //处理参数
        RequestDispatcher rd = this.getRequestDispatcher(request, exposeModelAsRequestParameter(model));
        if (rd == null) {
            throw new ServletException("Could not get RequestDispatcher for [" + this.getUrl() + "]: Check that the corresponding file exists within your web application archive!");
        }
        rd.forward(request, response);
    }

    @Nullable
    private RequestDispatcher getRequestDispatcher(HttpServletRequest request, String path) {
        return request.getRequestDispatcher(path);
    }

    private String exposeModelAsRequestParameter(Map<String, Object> model) {
        StringBuilder targetUrl = new StringBuilder(url);
        String params = StringUtil.fromMapToUrl(model);
        if(url.contains(Constant.RegularAbout.QUESTION_MARK)){
            if(!url.endsWith(Constant.RegularAbout.AND)){
                targetUrl.append(Constant.RegularAbout.AND);
            }
        }else{
            targetUrl.append(Constant.RegularAbout.QUESTION_MARK);
        }
        return targetUrl.append(params).toString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
