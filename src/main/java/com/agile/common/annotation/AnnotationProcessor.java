package com.agile.common.annotation;

import com.agile.common.base.Constant;
import com.agile.common.base.RETURN;
import com.agile.common.server.MainService;
import com.agile.common.util.ArrayUtil;
import com.agile.common.util.ObjectUtil;
import com.agile.common.util.StringUtil;
import com.agile.mvc.model.dao.Dao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佟盟 on 2018/1/5
 */
@Component
public class AnnotationProcessor implements EnvironmentAware {
    private Environment env;
    /**
     * 准备处理的注解
     */
    public static Class[] beforeClassAnnotations = {Properties.class};
    public static Class[] afterClassAnnotations = {Service.class};
    public static Class[] methodAnnotations = {Init.class};

    void Init(Init init, Object bean,Method method){
        method.setAccessible(true);
        if(!ObjectUtil.isEmpty(init)){
            try {
                method.invoke(bean);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    void Properties(Properties properties, Object bean) throws InstantiationException, IllegalAccessException {
        Class<?> targetClass = bean.getClass();
        if(ObjectUtil.isEmpty(properties))return;
            String prefix = properties.prefix();
            setProperties(bean,prefix);
    }

    void Service(Service service, Object bean){
        Class<?> targetClass = bean.getClass();
        if(targetClass.getGenericSuperclass() != MainService.class)return;
        String name = targetClass.getSimpleName();

        Method[] methods = targetClass.getDeclaredMethods();
        for(int i = 0 ; i < methods.length;i++){
            Method method = methods[i];
            if(method.getGenericReturnType() != RETURN.class)continue;
            JSONObject path = new JSONObject();
            String key = "/"+name+"/"+method.getName();
            API methodApi = method.getAnnotation(API.class);
            JSONArray tags = new JSONArray();
            String operationId = name+"_"+method.getName();
            if(!ObjectUtil.isEmpty(methodApi)){
                API.Method[] requestMethods = methodApi.method();
                for(int j=0;j<requestMethods.length;j++){
                    JSONObject request = new JSONObject();

                    Tag[] methodTags = methodApi.tag();
                    if(!ArrayUtil.isEmpty(methodTags)){
                        for(int k = 0 ; k < methodTags.length;k++){
                            Tag methodTag = methodTags[k];
                            String tagName = methodTag.name();
                            String description = methodTag.description();
                            tags.element(tagName);
                            if(!Constant.tagMap.containsKey(tagName)){
                                JSONObject tag = new JSONObject();
                                tag.put("name",tagName);
                                tag.put("description",description);
                                Constant.tags.element(tag);
                                Constant.tagMap.put(tagName,description);
                            }
                        }
                        request.put("tags",tags);
                    }
                    String summary = methodApi.summary();
                    request.put("summary",summary);

                    request.put("operationId",operationId);
                    JSONArray consumes = new JSONArray();
                    consumes.element("application/json");
                    consumes.element("application/xml");
                    request.put("consumes",consumes);
                    request.put("produces",consumes);

                    Param[] parameters = methodApi.parameters();
                    if(!ArrayUtil.isEmpty(parameters)){
                        JSONArray parametersJson = new JSONArray();
                        for(int k = 0 ; k < parameters.length;k++){
                            Param parameter = parameters[k];
                            JSONObject paramJson = new JSONObject();
                            paramJson.put("name",parameter.name());
                            paramJson.put("in",parameter.in());
                            paramJson.put("description",parameter.description());
                            paramJson.put("required",parameter.required());
                            paramJson.put("type",parameter.type().name().toLowerCase());
                            parametersJson.element(paramJson);
                        }
                        request.put("parameters",parametersJson);
                    }

                    Responses[] responses = methodApi.responses();
                    if(!ArrayUtil.isEmpty(responses)){
                        JSONObject responsesJsons = new JSONObject();
                        for(int k = 0 ; k < responses.length;k++){
                            Responses respons = responses[k];
                            JSONObject responsJson = new JSONObject();
                            responsJson.put("description",respons.description());
                            JSONObject ref = new JSONObject();
                            String objectClassName = respons.schema().getSimpleName();
                            if(!Constant.definitionMap.containsKey(objectClassName) && !"Object".equals(objectClassName)){
                                ref.put("$ref","#/definitions/"+objectClassName);
                                JSONObject object = new JSONObject();
                                if(respons.isArray()){
                                    JSONObject schemaInnner = new JSONObject();
                                    schemaInnner.put("type","array");
                                    schemaInnner.put("items",ref);
                                    responsJson.put("schema",schemaInnner);
                                }else{
                                    responsJson.put("schema",ref);
                                }
                                object.put("type","object");
                                JSONObject properties = new JSONObject();
                                Field[] fields = respons.schema().getDeclaredFields();
                                for(int l = 0 ; l < fields.length;l++){
                                    Field field = fields[l];
                                    field.setAccessible(true);
                                    if("serialVersionUID".equals(field.getName()))continue;
                                    JSONObject fieldJson = new JSONObject();
                                    fieldJson.put("type",field.getType().getSimpleName().toLowerCase());
                                    Remark remark = field.getDeclaredAnnotation(Remark.class);
                                    if(!ObjectUtil.isEmpty(remark)){
                                        fieldJson.put("description",remark.value());
                                    }
                                    properties.put(field.getName(),fieldJson);
                                }
                                object.put("properties",properties);
                                Constant.definitions.put(objectClassName,object);
                                Constant.definitionMap.put(objectClassName,"");
                            }
                            responsesJsons.put(respons.code(),responsJson);
                        }
                        request.put("responses",responsesJsons);
                    }
                    request.put("description",methodApi.description());
                    path.put(requestMethods[j].name().toLowerCase(),request);
                }
            }else{
                JSONObject request = new JSONObject();
                request.put("tags",tags.element("基础服务"));
                request.put("operationId",operationId);
                JSONArray consumes = new JSONArray();
                consumes.element("application/json");
//                consumes.element("application/xml");
                request.put("consumes",consumes);
                request.put("produces",consumes);
                JSONObject responsJsons = new JSONObject();
                JSONObject responsJson0 = new JSONObject();
                responsJson0.put("description",RETURN.SUCCESS.getMsg());
                responsJsons.put(RETURN.SUCCESS.getCode(),responsJson0);
                JSONObject responsJson1 = new JSONObject();
                responsJson1.put("description",RETURN.EXPRESSION.getMsg());
                responsJsons.put(RETURN.EXPRESSION.getCode(),responsJson1);
                request.put("responses",responsJsons);
                path.put("get",request);
            }
            Constant.paths.put(key,path);
            Constant.apiInfo.put("paths",Constant.paths);
            Constant.apiInfo.put("tags",Constant.tags);
            Constant.apiInfo.put("definitions",Constant.definitions);
        }
    }

    private void setProperties(Object target,String prefix) throws IllegalAccessException, InstantiationException {
        if(ObjectUtil.isEmpty(target))return;
        Class<?> targetClass = target.getClass();
        Field[] fields = targetClass.getDeclaredFields();
        for(int i = 0 ; i < fields.length;i++){
            Field field = fields[i];
            field.setAccessible(true);
            Class<?> type = field.getType();
            String name = field.getName();
            if(type.isAssignableFrom(List.class)){
                Type genericType = field.getGenericType();
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                if(ArrayUtil.isEmpty(typeArguments))continue;
                Class innerClass = (Class)typeArguments[0];


                List list = new ArrayList();
                int j = 0 ;
                boolean hasNext = true;

                if(ObjectUtil.isEmpty(innerClass.getPackage()) || innerClass.getPackage().getName().startsWith("java.")){
                    while (hasNext){
                        String key = prefix+"."+name+"["+j+"]";
                        if(j==0){
                            key = env.containsProperty(prefix+"."+name+"["+j+"]")?prefix+"."+name+"["+j+"]":prefix+"."+name;
                        }
                        if(env.containsProperty(key)){
                            Object temp = env.getProperty(key,innerClass);
                            list.add(temp);
                            j++;
                        }else{
                            hasNext = false;
                        }
                    }
                }else{
                    while (hasNext){
                        Object temp = innerClass.newInstance();
                        setProperties(temp,prefix+"."+name+"["+j+++"]");
                        if(ObjectUtil.compareValue(temp,innerClass.newInstance()) && j==1){
                            setProperties(temp,prefix+"."+name);
                        }
                        if(ObjectUtil.compareValue(temp,innerClass.newInstance()) && j!=1){
                            hasNext = false;
                            continue;
                        }
                        list.add(temp);
                    }
                }
                field.set(target,list);
            }else{
                if(ObjectUtil.isEmpty(type.getPackage()) || type.getPackage().getName().startsWith("java.")){
                    String key = prefix + "." + StringUtil.camelToUnderline(name);
                    if(env.containsProperty(key))
                    field.set(target,env.getProperty(key,type));
                }else{
                    Object temp = type.newInstance();
                    setProperties(temp,prefix+"."+StringUtil.camelToUnderline(name));
                    field.set(target,temp);
                }
            }
        }
    }

    @Override
    public void setEnvironment(@NotNull Environment environment) {
        this.env = environment;
    }
}
