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
    private static DatabaseMetaData databaseMetaData;
    private static Statement statement;
    private static Connection connection;

    private static void initDB() throws ClassNotFoundException, SQLException {
        PropertiesUtil propertiesUtil = new PropertiesUtil("./src/main/resources/com/agile/configure/agile.properties");

        List<String> tableNames = new ArrayList<>();
        //加载数据库驱动类
        Class.forName(propertiesUtil.getProperty("agile.druid.driver_class_name")) ;
        //建立数据库连接
        if(ObjectUtil.isEmpty(connection))connection =  DriverManager.getConnection(propertiesUtil.getProperty("agile.druid.jdbc_url_prefix")+propertiesUtil.getProperty("agile.druid.data_base_ip")+":"+propertiesUtil.getProperty("agile.druid.data_base_post")+"/"+propertiesUtil.getProperty("agile.druid.data_base_name")+"?"+propertiesUtil.getProperty("agile.druid.data_base_url_param") , propertiesUtil.getProperty("agile.druid.data_base_username") , propertiesUtil.getProperty("agile.druid.data_base_password") ) ;

        if(ObjectUtil.isEmpty(databaseMetaData))databaseMetaData = connection.getMetaData();

        if(ObjectUtil.isEmpty(statement))statement = connection.createStatement();
    }

    private static ResultSet excuteSQL(Statement statement,String sql) throws ClassNotFoundException, SQLException {
        return resultSet = statement.executeQuery(sql);
    }

    private static void destroyDB() throws ClassNotFoundException, SQLException {

        if(!ObjectUtil.isEmpty(resultSet))resultSet.close();

        if(!ObjectUtil.isEmpty(statement))statement.close();

        if(!ObjectUtil.isEmpty(connection))connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        PropertiesUtil propertiesUtil = new PropertiesUtil("./src/main/resources/com/agile/configure/agile.properties");
        CodeGenerator.initDB();

        try {


            //获取表类别名称
            String catalog = CodeGenerator.connection.getCatalog();
            String schema = CodeGenerator.connection.getSchema();

            //获取表信息
            ResultSet tablesData = CodeGenerator.databaseMetaData.getTables(catalog, schema,"log%",new String[]{"TABLE"});
            while(tablesData.next()){
                Map<String,Object> data = new HashMap<>();
                String className,tableName,primaryKeyColumnName = null,primaryKeyColumnType = null,primaryKeyPropertyName = null,primaryKeyPropertyType = null,propertyType;
                boolean primaryKeyColumnIsAutoincrement;
                int primaryKeyColumnSize;
                List<HashMap<String,String>> columnList = new ArrayList<>();
                List<String> importList = new ArrayList<>();
                //获取表名字
                tableName = tablesData.getString("TABLE_NAME");
                className = StringUtil.toUpperName(tableName);


                //根据表名获取字段信息
                ResultSet columnsData = CodeGenerator.databaseMetaData.getColumns(catalog,schema, tableName,"%");
                ResultSet primaryKeyResultSet = CodeGenerator.databaseMetaData.getPrimaryKeys(catalog,schema, tableName);


                while(primaryKeyResultSet.next()){
                    //主键字段名称
                    primaryKeyColumnName = primaryKeyResultSet.getString("COLUMN_NAME");
                }

                while(columnsData.next()) {
                    HashMap<String,String> param = new HashMap<>();

                    //是否为主键
                    param.put("isPrimaryKey","false");

                    //字段名称
                    param.put("columnName",columnsData.getString("COLUMN_NAME"));

                    //属性名
                    param.put("propertyName",StringUtil.toLowerName(columnsData.getString("COLUMN_NAME")));

                    //get方法
                    param.put("getMethod","get"+StringUtil.toUpperName(columnsData.getString("COLUMN_NAME")));

                    //set方法
                    param.put("setMethod","set"+StringUtil.toLowerName(columnsData.getString("COLUMN_NAME")));

                    //字段类型
                    param.put("columnType",columnsData.getString("TYPE_NAME"));

                    //属性类型
                    propertyType = propertiesUtil.getProperty("agile.generator.column_type."+columnsData.getString("TYPE_NAME").toLowerCase());
                    param.put("propertyType",propertyType);

                    //API导入
                    if ("Timestamp".equals(propertyType))importList.add("java.sql.Timestamp;");
                    if ("Date".equals(propertyType))importList.add("java.sql.Date;");

                    //是否自增长
                    param.put("isAutoincrement",columnsData.getString("IS_AUTOINCREMENT"));

                    //字段大小
                    param.put("datasize",columnsData.getString("COLUMN_SIZE"));

                    //小数精度
                    param.put("digits",columnsData.getString("DECIMAL_DIGITS"));

                    //是否可为空
                    param.put("nullable",columnsData.getString("NULLABLE"));

                    //字段默认值
                    param.put("columnDef",columnsData.getString("COLUMN_DEF"));

                    //表中的列的索引
                    param.put("ordinalPosition",columnsData.getString("ORDINAL_POSITION"));

                    //备注
                    param.put("remarks",columnsData.getString("REMARKS"));

                    //处理主键
                    if(StringUtil.compare(primaryKeyColumnName,columnsData.getString("COLUMN_NAME"))){

                        //主键字段类型
                        primaryKeyPropertyType = propertyType;

                        //是否为主键
                        param.put("isPrimaryKey","true");

                    }
                    columnList.add(param);
                }

                //文件所在包路径
                data.put("repositoryPackage","com.agile.mvc.model.dao");
                data.put("servicePackage","com.agile.mvc.service");
                data.put("entityPackage","com.agile.mvc.model.entity");

                //文件导入
                data.put("importList",importList);

                //参数
                data.put("className",className);
                data.put("tableName",tableName);
                data.put("schemaName",schema);
                data.put("catalogName",catalog);

                //主键参数
                data.put("primaryKeyPropertyType",primaryKeyPropertyType);

                //字段参数
                data.put("columnList",columnList);
                Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
                cfg.setDirectoryForTemplateLoading(new File("./src/main/java/com/agile/common/tools"));
                cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));

                //Entity生成器
                Template entityTemp = cfg.getTemplate("Entity.ftl");
                String entityFileName = className+"Entity.java";
                File entityFile = new File("./src/main/java/com/agile/mvc/model/entity/" + entityFileName);
                FileWriter entityFileFw = new FileWriter(entityFile);
                BufferedWriter entityFileBw = new BufferedWriter(entityFileFw);
                entityTemp.process(data, entityFileBw);
                entityFileBw.flush();
                entityFileFw.close();

                //DAO生成器
                Template repositoryTemp = cfg.getTemplate("Repository.ftl");
                String repositoryFileName = className+"Repository.java";
                File repositoryFile = new File("./src/main/java/com/agile/mvc/model/dao/" + repositoryFileName);
                FileWriter repositoryFileFw = new FileWriter(repositoryFile);
                BufferedWriter repositoryFileBw = new BufferedWriter(repositoryFileFw);
                repositoryTemp.process(data, repositoryFileBw);
                repositoryFileBw.flush();
                repositoryFileFw.close();

                //service生成器
                Template serviceTemp = cfg.getTemplate("Service.ftl");
                String ServiceFileName = className+"Service.java";
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
        CodeGenerator.destroyDB();
    }
}