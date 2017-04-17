package com.agile.common.tools;

import com.agile.common.util.PropertiesUtil;
import com.agile.common.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Created by mydeathtrial on 2017/3/10
 */
public class DaoGenerator {

    private List<String> getTables(String sql) throws ClassNotFoundException, SQLException {
        PropertiesUtil propertiesUtil = new PropertiesUtil("./src/main/resources/com/agile/configure/agile.properties");

        List<String> tableNames = new ArrayList<>();
        //加载数据库驱动类
        Class.forName(propertiesUtil.getProperty("agile.druid.driver_class_name")) ;
        //建立数据库连接
        Connection connection =  DriverManager.getConnection(propertiesUtil.getProperty("agile.druid.jdbc_url_prefix")+propertiesUtil.getProperty("agile.druid.data_base_ip")+":"+propertiesUtil.getProperty("agile.druid.data_base_post")+"/"+propertiesUtil.getProperty("agile.druid.data_base_name")+"?"+propertiesUtil.getProperty("agile.druid.data_base_url_param") , propertiesUtil.getProperty("agile.druid.data_base_username") , propertiesUtil.getProperty("agile.druid.data_base_password") ) ;

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            tableNames.add(resultSet.getString(1));
        }

        resultSet.close();

        statement.close();

        connection.close();

        return tableNames;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        PropertiesUtil propertiesUtil = new PropertiesUtil("./src/main/resources/com/agile/configure/agile.properties");
        DaoGenerator generator = new DaoGenerator();
        List<String> dataSet = generator.getTables(propertiesUtil.getProperty("agile.generator.sql"));
        try {
            Map<String,String> data = new HashMap<>();
            for (String tableName:dataSet) {
                String tableNameOfJava = StringUtil.toUpperName(tableName);
                data.put("repositoryPackage","com.agile.mvc.model.dao");
                data.put("servicePackage","com.agile.mvc.service");
                data.put("tableName",tableNameOfJava);
                data.put("keyType",propertiesUtil.getProperty("agile.generator.key_type"));
                Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
                cfg.setDirectoryForTemplateLoading(new File("./src/main/java/com/agile/common/tools"));
                cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));

                //DAO生成器
                Template repositoryTemp = cfg.getTemplate("Repository.ftl");
                String repositoryFileName = tableNameOfJava+"Repository.java";
                File repositoryFile = new File("./src/main/java/com/agile/mvc/model/dao/" + repositoryFileName);
                FileWriter repositoryFileFw = new FileWriter(repositoryFile);
                BufferedWriter repositoryFileBw = new BufferedWriter(repositoryFileFw);
                repositoryTemp.process(data, repositoryFileBw);
                repositoryFileBw.flush();
                repositoryFileFw.close();

                //service生成器
                Template serviceTemp = cfg.getTemplate("Service.ftl");
                String ServiceFileName = tableNameOfJava+"Service.java";
                File serviceFile = new File("./src/main/java/com/agile/mvc/service/" + ServiceFileName);
                FileWriter serviceFileFw = new FileWriter(serviceFile);
                BufferedWriter serviceFileBw = new BufferedWriter(serviceFileFw);
                serviceTemp.process(data, serviceFileBw);
                serviceFileBw.flush();
                serviceFileFw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}