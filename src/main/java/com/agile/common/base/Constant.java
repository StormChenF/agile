package com.agile.common.base;

import com.agile.common.util.ServletUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 佟盟 on 2017/9/2
 */
public class Constant {
    /**
     * 响应信息相关
     */
    public static class ResponseAbout {
        public final static String HEAD = "head";
        public final static String RESULT = "result";
        public final static String STATE = "state";
        public final static String MSG = "msg";
        public final static String INFO = "info";
        public final static String APP = "app";
        public final static String SERVICE = "service";
        public final static String METHOD = "method";
        public final static String IP = "ip";
        public final static String URL = "url";
    }

    /**
     * 文件相关
     */
    public static class FileAbout {
        public final static String FILE_NAME = "fileName";
        public final static String FILE_SIZE = "fileSize";
        public final static String CONTENT_TYPE = "contentType";
        public final static String UP_LOUD_FILE_INFO = "upLoadFileInfo";
        public final static String SERVICE_LOGGER_FILE = "service";
    }

    /**
     * 响应头信息相关
     */
    public static class HeaderAbout {
        public final static String ATTACHMENT = "attachment";
    }

    /**
     * 正则表达式
     */
    public static class RegularAbout {
        public final static String NULL = "";
        public final static String SPOT = ".";
        public final static String COMMA = ",";
        public final static String QUESTION_MARK = "?";
        public final static String SLASH = "/";
        public final static String BACKSLASH = "\\";
        public final static String AND = "&";
        public final static String EQUAL = "=";
        public final static String AFTER_PARAM = "forward[-_*%#$@+=()^!~`|.,/a-zA-Z0-9]+[&]?";
        public final static String HUMP = "((?=[\\x21-\\x7e]+)[^A-Za-z0-9])";
        public final static String UPER = "[A-Z]";
    }

    public static JSONObject apiInfo = new JSONObject();
    public static JSONArray tags = new JSONArray();
    public static JSONArray schemes = new JSONArray();
    public static JSONObject paths = new JSONObject();
    public static JSONObject definitions = new JSONObject();
    public static Map<String,String> tagMap = new HashMap<>();
    static {
        JSONObject contact = new JSONObject();
        contact.put("email","mydeathtrial@163.com");

        JSONObject info = new JSONObject();
        info.put("version","1.0.0");
        info.put("title","Agile API平台");
        info.put("contact",contact);

        schemes.element("http");
        schemes.element("https");

        JSONObject scopes = new JSONObject();
        scopes.put("write:pets","");
        scopes.put("read:pets","");

        JSONObject apiKey = new JSONObject();
        apiKey.put("type","apiKey");
        apiKey.put("name","api_key");
        apiKey.put("in","header");

        JSONObject petstoreAuth = new JSONObject();
        petstoreAuth.put("type","oauth2");
        petstoreAuth.put("authorizationUrl","oauth2-redirect.html");
        petstoreAuth.put("flow","implicit");
        petstoreAuth.put("scopes",scopes);
        petstoreAuth.put("api_key",apiKey);

        JSONObject securityDefinitions = new JSONObject();
        securityDefinitions.put("petstore_auth",petstoreAuth);

        JSONObject externalDocs = new JSONObject();
        externalDocs.put("description","");
        externalDocs.put("url","");

        JSONObject defaultTag = new JSONObject();
        defaultTag.put("name","基础服务");
        defaultTag.put("description","系统基础服务");
        tags.element(defaultTag);

        apiInfo.put("swagger","2.0");
        apiInfo.put("info",info);
        apiInfo.put("tags",tags);
        apiInfo.put("schemes",schemes);
        apiInfo.put("paths",paths);
        apiInfo.put("securityDefinitions",securityDefinitions);
        apiInfo.put("definitions",definitions);
        apiInfo.put("externalDocs",externalDocs);
    }
}
