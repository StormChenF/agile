package com.agile.common.tools;

import com.agile.common.util.PropertiesUtil;
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
        Connection connection =  DriverManager.getConnection(PropertiesUtil.getProperties("agile.druid.jdbc_url") , PropertiesUtil.getProperties("agile.druid.username") , PropertiesUtil.getProperties("agile.druid.password") ) ;

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
            Map root = new HashMap();
            root.put("str", "hello world!");
            List data = new ArrayList();
            data.add("11");
            data.add("12");
            data.add("13");
            root.put("data", data);

            Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            cfg.setDirectoryForTemplateLoading(new File(DaoGenerator.class.getResource("/").toString()));
            cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
            Template temp = cfg.getTemplate("daohome.ftl");
            String fileName = "demo.java";
            File file = new File("D:/" + fileName);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            temp.process(root, bw);
            bw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
