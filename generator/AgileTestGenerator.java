import com.agile.common.base.RETURN;
import com.agile.common.util.PropertiesUtil;
import com.agile.common.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mydeathtrial on 2017/5/10.
 */
public class AgileTestGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        PropertiesUtil propertiesUtil = new PropertiesUtil("./test/main/resources/com/agile/configure/agile.properties");
        File directory = new File("./src/main/java/com/agile/mvc/service");
        if (directory.isDirectory()){
            String[] files = directory.list();
            for (int i = 0 ; i < files.length ; i++){
                //分割获取类名
                String serviceName = files[i].replace(".java","");
                String entityName = null;
                Map<String, Object> data = new HashMap<>();
                List<Map<String, String>> methodList = new ArrayList<>();
                List<String> propertyList = new ArrayList<>();
                //循环处理各个类
                try {
                    Class<?> clazz = Class.forName("com.agile.mvc.service." + serviceName);
                    Method[] methods = clazz.getMethods();
                    for (int j = 0 ; j < methods.length;j++){
                        if (RETURN.class.getTypeName().equals(methods[j].getGenericReturnType().getTypeName()) && !methods[j].getName().equals("executeMethod")){
                            String methodName = methods[j].getName();
                            Map<String,String> map = new HashMap<>();
                            map.put("methodName",methodName);
                            map.put("url","/"+propertiesUtil.getProperty("agile.project.name")+"/"+serviceName+"/"+methodName);
                            methodList.add(map);
                        }
                    }

                    String servicePrefix = propertiesUtil.getProperty("agile.generator.service_prefix");
                    String serviceSuffix = propertiesUtil.getProperty("agile.generator.service_suffix");
                    String entityPrefix = propertiesUtil.getProperty("agile.generator.entity_prefix");
                    String entitySuffix = propertiesUtil.getProperty("agile.generator.entity_suffix");
                    if (serviceName.startsWith(servicePrefix)){
                        entityName = serviceName.replaceFirst(servicePrefix,"");
                    }
                    if (serviceName.endsWith(serviceSuffix)){
                        entityName = entityName.substring(0,serviceName.length()-serviceSuffix.length());
                    }
                    if(!StringUtil.isEmpty(entityPrefix)){
                        entityName = entityPrefix + entityName;
                    }
                    if(!StringUtil.isEmpty(entitySuffix)){
                        entityName = entityName + entitySuffix;
                    }
                    Class<?> entity = Class.forName("com.agile.mvc.model.entity." + entityName);
                    Method[] entityMethods = entity.getMethods();
                    for(int j = 0 ; j < entityMethods.length ; j++){
                        String entityMethodName = entityMethods[j].getName();
                        if (entityMethodName.startsWith("set")){
                            propertyList.add(StringUtil.toLowerName(entityMethodName));
                        }
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("未找到文件：com.agile.mvc.service."+serviceName);
                    continue;
                }
                //数据装填
                data.put("className",serviceName);
                data.put("methodList",methodList);
                data.put("propertyList",propertyList);

                //生成器
                Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
                cfg.setDirectoryForTemplateLoading(new File("./generator"));
                cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
                Template testTemp = cfg.getTemplate("agileTemplate/Test.ftl");
                String testFileName = serviceName + "Test.java";
                File testFile = new File("./test/main/java/com/agile/mvc/service/" + testFileName);
                FileWriter testFileFw = new FileWriter(testFile);
                BufferedWriter testFileBw = new BufferedWriter(testFileFw);
                testTemp.process(data, testFileBw);
                testFileBw.flush();
                testFileFw.close();
            }
        }

    }
}
