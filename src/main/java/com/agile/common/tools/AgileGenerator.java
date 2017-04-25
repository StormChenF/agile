package com.agile.common.tools;

import com.agile.common.util.ClassUtil;
import com.agile.common.util.DataBaseUtil;
import com.agile.common.util.PropertiesUtil;
import com.agile.common.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mydeathtrial on 2017/4/20
 */
public class AgileGenerator {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        PropertiesUtil propertiesUtil = new PropertiesUtil("./src/main/resources/com/agile/configure/agile.properties");
        DataBaseUtil.initDB();
        try {

            //获取表类别名称
            String catalog = DataBaseUtil.connection.getCatalog();
            String schema = DataBaseUtil.connection.getSchema();

            //获取表信息
            ResultSet tablesData = DataBaseUtil.databaseMetaData.getTables(catalog, schema, propertiesUtil.getProperty("agile.generator.table_name"), new String[]{"TABLE"});

            while (tablesData.next()) {

                Map<String, Object> data = new HashMap<>();
                String className, tableName, primaryKeyColumnName = null, primaryKeyPropertyType = null, propertyType;

                List<HashMap<String, String>> columnList = new ArrayList<>();
                List<String> importList = new ArrayList<>();
                List<String> indexList = new ArrayList<>();
                //获取表名字
                tableName = tablesData.getString("TABLE_NAME");
                className = StringUtil.toUpperName(tableName);

                //获取主键信息
                ResultSet primaryKeyResultSet = DataBaseUtil.databaseMetaData.getPrimaryKeys(catalog, schema, tableName);

                //根据表名获取字段信息
                ResultSet columnsData = DataBaseUtil.databaseMetaData.getColumns(catalog, schema, tableName, "%");

                while (primaryKeyResultSet.next()) {
                    //主键字段名称
                    primaryKeyColumnName = primaryKeyResultSet.getString("COLUMN_NAME");
                }

                while (columnsData.next()) {
                    //参数容器
                    HashMap<String, String> param = new HashMap<>();

                    //是否为主键
                    param.put("isPrimaryKey", "false");

                    //字段名称
                    param.put("columnName", columnsData.getString("COLUMN_NAME"));

                    //属性名
                    param.put("propertyName", StringUtil.toLowerName(columnsData.getString("COLUMN_NAME").toLowerCase()));

                    //get方法
                    param.put("getMethod", "get" + StringUtil.toUpperName(columnsData.getString("COLUMN_NAME")));

                    //set方法
                    param.put("setMethod", "set" + StringUtil.toUpperName(columnsData.getString("COLUMN_NAME")));

                    //字段类型
                    param.put("columnType", columnsData.getString("TYPE_NAME"));

                    //属性类型
                    propertyType = propertiesUtil.getProperty("agile.generator.column_type." + columnsData.getString("TYPE_NAME").toLowerCase());
                    param.put("propertyType", propertyType);

                    //API导入
                    if ("Timestamp".equals(propertyType)) importList.add("java.sql.Timestamp;");
                    if ("Date".equals(propertyType)) importList.add("java.sql.Date;");
                    if ("Clob".equals(propertyType)) importList.add("import java.sql.Clob;");
                    if ("Blob".equals(propertyType)) importList.add("import java.sql.Blob;");

                    //是否自增长
                    param.put("isAutoincrement", columnsData.getString("IS_AUTOINCREMENT"));

                    //字段大小
                    param.put("datasize", columnsData.getString("COLUMN_SIZE"));

                    //小数精度
                    param.put("digits", columnsData.getString("DECIMAL_DIGITS"));

                    //是否可为空
                    param.put("nullable", "0".equals(columnsData.getString("NULLABLE")) ? "true" : "false");

                    //字段默认值
                    param.put("columnDef", columnsData.getString("COLUMN_DEF"));

                    //表中的列的索引
                    param.put("ordinalPosition", columnsData.getString("ORDINAL_POSITION"));

                    //备注
                    param.put("remarks", columnsData.getString("REMARKS"));

                    //处理主键
                    if (StringUtil.compare(primaryKeyColumnName, columnsData.getString("COLUMN_NAME"))) {

                        //主键字段类型
                        primaryKeyPropertyType = ClassUtil.toWrapperNameFromName(propertyType);

                        //是否为主键
                        param.put("isPrimaryKey", "true");

                    }
                    columnList.add(param);
                }

                //文件所在包路径
                data.put("repositoryPackage", "com.agile.mvc.model.dao");
                data.put("servicePackage", "com.agile.mvc.service");
                data.put("entityPackage", "com.agile.mvc.model.entity");

                //文件导入
                data.put("importList", importList);

                //唯一索引信息
                data.put("indexList", indexList);

                //参数
                data.put("className", className);
                data.put("tableName", tableName);
                data.put("schemaName", schema);
                data.put("catalogName", catalog);

                //主键参数
                data.put("primaryKeyPropertyType", primaryKeyPropertyType);

                //字段参数
                data.put("columnList", columnList);
                Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
                cfg.setDirectoryForTemplateLoading(new File("./src/main/java/com/agile/common/tools"));
                cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));

                //Entity生成器
                Template entityTemp = cfg.getTemplate("Entity.ftl");
                String entityFileName = className + "Entity.java";
                File entityFile = new File("./src/main/java/com/agile/mvc/model/entity/" + entityFileName);
                FileWriter entityFileFw = new FileWriter(entityFile);
                BufferedWriter entityFileBw = new BufferedWriter(entityFileFw);
                entityTemp.process(data, entityFileBw);
                entityFileBw.flush();
                entityFileFw.close();

                //DAO生成器
                Template repositoryTemp = cfg.getTemplate("Repository.ftl");
                String repositoryFileName = className + "Repository.java";
                File repositoryFile = new File("./src/main/java/com/agile/mvc/model/dao/" + repositoryFileName);
                FileWriter repositoryFileFw = new FileWriter(repositoryFile);
                BufferedWriter repositoryFileBw = new BufferedWriter(repositoryFileFw);
                repositoryTemp.process(data, repositoryFileBw);
                repositoryFileBw.flush();
                repositoryFileFw.close();

                //service生成器
                Template serviceTemp = cfg.getTemplate("Service.ftl");
                String ServiceFileName = className + "Service.java";
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
        DataBaseUtil.destroyDB();
    }
}
