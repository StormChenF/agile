package com.agile.common.tools;

import com.agile.common.util.BooleanUtil;
import com.agile.common.util.PropertiesUtil;
import com.agile.common.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.sql.*;
import java.util.*;


/**
 * Created by mydeathtrial on 2017/3/10
 */
public class DaoGenerator {
    private ResultSet resultSet;
    private DatabaseMetaData databaseMetaData;
    private Statement statement;
    private Connection connection;

    private void initDB() throws ClassNotFoundException, SQLException {
        PropertiesUtil propertiesUtil = new PropertiesUtil("./src/main/resources/com/agile/configure/agile.properties");

        List<String> tableNames = new ArrayList<>();
        //加载数据库驱动类
        Class.forName(propertiesUtil.getProperty("agile.druid.driver_class_name")) ;
        //建立数据库连接
        this.connection =  DriverManager.getConnection(propertiesUtil.getProperty("agile.druid.jdbc_url_prefix")+propertiesUtil.getProperty("agile.druid.data_base_ip")+":"+propertiesUtil.getProperty("agile.druid.data_base_post")+"/"+propertiesUtil.getProperty("agile.druid.data_base_name")+"?"+propertiesUtil.getProperty("agile.druid.data_base_url_param") , propertiesUtil.getProperty("agile.druid.data_base_username") , propertiesUtil.getProperty("agile.druid.data_base_password") ) ;

        this.databaseMetaData = connection.getMetaData();

        this.statement = connection.createStatement();
    }

    private ResultSet excuteSQL(Statement statement,String sql) throws ClassNotFoundException, SQLException {
        return this.resultSet = statement.executeQuery(sql);
    }

    private void destroyDB() throws ClassNotFoundException, SQLException {
        
        this.resultSet.close();

        this.statement.close();

        this.connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        PropertiesUtil propertiesUtil = new PropertiesUtil("./src/main/resources/com/agile/configure/agile.properties");
        DaoGenerator generator = new DaoGenerator();
        generator.initDB();

        try {
            Map<String,Object> data = new HashMap<>();
            String className,tableName,primaryKeyColumnName = null,primaryKeyColumnType = null,primaryKeyPropertyName = null,primaryKeyPropertyType = null;
            boolean primaryKeyColumnIsAutoincrement;
            int primaryKeyColumnSize;
            List<HashMap<String,String>> columsList = new ArrayList<>();
            //获取表类别名称
            String catalog = generator.connection.getCatalog();
            String schema = generator.connection.getSchema();

            //获取表信息
            ResultSet tablesData = generator.databaseMetaData.getTables(catalog, schema,"%",new String[]{"TABLE"});
            while(tablesData.next()){
                //获取表名字
                tableName = tablesData.getString("TABLE_NAME");
                className = StringUtil.toUpperName(tableName);


                //根据表名获取字段信息
                ResultSet columnsData = generator.databaseMetaData.getColumns(catalog,schema, tableName,"%");
                ResultSet primaryKeyResultSet = generator.databaseMetaData.getPrimaryKeys(catalog,schema, tableName);


                while(primaryKeyResultSet.next()){
                    //主键字段名称
                    primaryKeyColumnName = primaryKeyResultSet.getString("COLUMN_NAME");
                }

                while(columnsData.next()) {
                    HashMap<String,String> param = new HashMap<>();
                    //字段名称
                    param.put("columnName",columnsData.getString("COLUMN_NAME"));

                    //属性名
                    param.put("propertyName",StringUtil.toLowerName(columnsData.getString("COLUMN_NAME")));

                    //字段类型
                    param.put("columnType",columnsData.getString("TYPE_NAME"));

                    //属性类型
                    param.put("propertyType",propertiesUtil.getProperty("agile.generator.column_type."+columnsData.getString("TYPE_NAME").toLowerCase()));

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
                        //主键属性名称
                        primaryKeyPropertyName = StringUtil.toLowerName(columnsData.getString("COLUMN_NAME"));

                        //主键字段类型
                        primaryKeyColumnType = columnsData.getString("COLUMN_NAME");

                        //主键属性类型
                        primaryKeyPropertyType = propertiesUtil.getProperty("agile.generator.column_type."+columnsData.getString("TYPE_NAME").toLowerCase());

                        //主键是否自增长
                        primaryKeyColumnIsAutoincrement = BooleanUtil.toBoolean(columnsData.getString("IS_AUTOINCREMENT"));

                        //主键大小
                        primaryKeyColumnSize = columnsData.getInt("COLUMN_SIZE");

                        continue;

                    }
                    columsList.add(param);
                }

                //文件所在包路径
                data.put("repositoryPackage","com.agile.mvc.model.dao");
                data.put("servicePackage","com.agile.mvc.service");
                data.put("entityPackage","com.agile.mvc.model.entity");

                //参数
                data.put("className",className);
                data.put("tableName",tableName);
                data.put("schemaName",schema);
                data.put("catalogName",catalog);

                //主键参数
                data.put("primaryKeyPropertyName",primaryKeyPropertyName);
                data.put("primaryKeyPropertyType",primaryKeyPropertyType);
                data.put("primaryKeyColumnName",primaryKeyColumnName);
                data.put("primaryKeyColumnType",primaryKeyColumnType);

                //字段参数
                data.put("columsList",columsList);
                Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
                cfg.setDirectoryForTemplateLoading(new File("./src/main/java/com/agile/common/tools"));
                cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));

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
        generator.destroyDB();
    }
}