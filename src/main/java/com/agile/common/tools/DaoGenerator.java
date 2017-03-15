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
        List<String> tableNames = new ArrayList<>();
        //加载数据库驱动类
        Class.forName(PropertiesUtil.getProperties("agile.druid.driver_class_name")) ;
        //建立数据库连接
        Connection connection =  DriverManager.getConnection(PropertiesUtil.getProperties("agile.druid.jdbc_url_prefix")+PropertiesUtil.getProperties("agile.druid.data_base_ip")+":"+PropertiesUtil.getProperties("agile.druid.data_base_post")+"/"+PropertiesUtil.getProperties("agile.druid.data_base_name")+"?"+PropertiesUtil.getProperties("agile.druid.data_base_url_param") , PropertiesUtil.getProperties("agile.druid.data_base_username") , PropertiesUtil.getProperties("agile.druid.data_base_password") ) ;

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
        DaoGenerator generator = new DaoGenerator();
        List<String> dataSet = generator.getTables(PropertiesUtil.getProperties("agile.generator.sql"));
        try {
            Map<String,String> data = new HashMap<>();
            for (String tableName:dataSet) {
                String tableNameOfJava = StringUtil.toName(tableName)+"Entity";
                data.put("package","com.agile.mvc.model.dao");
                data.put("tableName",tableNameOfJava);
                data.put("keyType",PropertiesUtil.getProperties("agile.generator.key_type"));
                Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
                cfg.setDirectoryForTemplateLoading(new File("./src/main/java/com/agile/common/tools"));
                cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
                Template temp = cfg.getTemplate("Repository.ftl");
                String fileName = tableNameOfJava+"Repository.java";
                File file = new File("./src/main/java/com/agile/mvc/model/dao/" + fileName);
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                temp.process(data, bw);
                bw.flush();
                fw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
