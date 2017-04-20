package com.agile.common.tools;

import com.agile.common.util.BooleanUtil;
import com.agile.common.util.ObjectUtil;
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
public class CodeGenerator {
    private static ResultSet resultSet;
    static DatabaseMetaData databaseMetaData;
    private static Statement statement;
    static Connection connection;

    static void initDB() throws ClassNotFoundException, SQLException {
        PropertiesUtil propertiesUtil = new PropertiesUtil("./src/main/resources/com/agile/configure/agile.properties");

        List<String> tableNames = new ArrayList<>();
        //加载数据库驱动类
        Class.forName(propertiesUtil.getProperty("agile.druid.driver_class_name")) ;
        //建立数据库连接
        if(ObjectUtil.isEmpty(connection))connection =  DriverManager.getConnection(propertiesUtil.getProperty("agile.druid.jdbc_url_prefix")+propertiesUtil.getProperty("agile.druid.data_base_ip")+":"+propertiesUtil.getProperty("agile.druid.data_base_post")+"/"+propertiesUtil.getProperty("agile.druid.data_base_name")+"?"+propertiesUtil.getProperty("agile.druid.data_base_url_param") , propertiesUtil.getProperty("agile.druid.data_base_username") , propertiesUtil.getProperty("agile.druid.data_base_password") ) ;

        if(ObjectUtil.isEmpty(databaseMetaData))databaseMetaData = connection.getMetaData();

        if(ObjectUtil.isEmpty(statement))statement = connection.createStatement();
    }

    public static ResultSet excuteSQL(Statement statement,String sql) throws ClassNotFoundException, SQLException {
        return resultSet = statement.executeQuery(sql);
    }

    static void destroyDB() throws ClassNotFoundException, SQLException {

        if(!ObjectUtil.isEmpty(resultSet))resultSet.close();

        if(!ObjectUtil.isEmpty(statement))statement.close();

        if(!ObjectUtil.isEmpty(connection))connection.close();
    }
}