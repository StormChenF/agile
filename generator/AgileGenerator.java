import com.agile.common.util.ClassUtil;
import com.agile.common.util.DataBaseUtil;
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
import java.net.URISyntaxException;
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
    private static Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    static {
        try {
            cfg.setDirectoryForTemplateLoading(new File("./generator/template"));
            cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataBaseUtil.initDB();
        try {

            //获取表类别名称
            String catalog = DataBaseUtil.connection.getCatalog();
            String schema = DataBaseUtil.connection.getSchema();

            //获取表信息
            ResultSet tablesData = DataBaseUtil.databaseMetaData.getTables(catalog, schema, PropertiesUtil.getProperty("agile.generator.table_name"), new String[]{"TABLE"});

            List<Map<String, Object>> tableInfoList = new ArrayList<>();

            //模板配置


            while (tablesData.next()) {

                Map<String, Object> data = new HashMap<>();
                String className, tableName, primaryKeyColumnName = null, primaryKeyPropertyName = null,primaryKeyPropertyType = null, propertyType;

                List<HashMap<String, String>> columnList = new ArrayList<>();
                List<String> importList = new ArrayList<>();
                List<String> indexList = new ArrayList<>();
                //获取表名字
                tableName = tablesData.getString("TABLE_NAME");
                className = StringUtil.toUpperName(tableName);
                String tableComment;
                switch (DataBaseUtil.type){
                    case "mysql":
                        ResultSet otherInfo = DataBaseUtil.excuteSQL("SELECT TABLE_COMMENT FROM information_schema.TABLES WHERE table_schema='" + catalog + "' AND TABLE_NAME = '" + tableName + "'");
                        while (otherInfo.next()){
                            tableComment = otherInfo.getString("TABLE_COMMENT");
                            data.put("tableComment", tableComment);
                        }
                        break;
                    case "oracle":
                        break;
                }

                //获取主键信息
                ResultSet primaryKeyResultSet = DataBaseUtil.databaseMetaData.getPrimaryKeys(catalog, schema, tableName);

                //根据表名获取字段信息
                ResultSet columnsData = DataBaseUtil.databaseMetaData.getColumns(catalog, schema, tableName, "%");

                while (primaryKeyResultSet.next()) {
                    //主键字段名称
                    primaryKeyColumnName = primaryKeyResultSet.getString("COLUMN_NAME").toLowerCase();
                }

                while (columnsData.next()) {
                    //参数容器
                    HashMap<String, String> param = new HashMap<>();

                    //是否为主键
                    param.put("isPrimaryKey", "false");

                    //字段名称
                    String columnName = columnsData.getString("COLUMN_NAME").toLowerCase();
                    param.put("columnName", columnName);

                    //属性名
                    param.put("propertyName", StringUtil.toLowerName(columnName));

                    //字段类型
                    param.put("columnType", columnsData.getString("TYPE_NAME"));

                    //属性类型
                    propertyType = PropertiesUtil.getProperty("agile.generator.column_type." + columnsData.getString("TYPE_NAME").toLowerCase());

                    //get方法
                    param.put("getMethod", "get" + StringUtil.toUpperName(columnName));

                    //set方法
                    param.put("setMethod", "set" + StringUtil.toUpperName(columnName));

                    //API导入
                    if ("Timestamp".equals(propertyType)) importList.add("java.sql.Timestamp;");
                    if ("Date".equals(propertyType)) importList.add("java.util.Date;");
                    if ("Clob".equals(propertyType)) importList.add("import java.sql.Clob;");
                    if ("Blob".equals(propertyType)) importList.add("import java.sql.Blob;");

                    //是否自增长
                    param.put("isAutoincrement", columnsData.getString("IS_AUTOINCREMENT"));

                    //字段大小
                    param.put("datasize", columnsData.getString("COLUMN_SIZE"));

                    //小数精度
                    param.put("digits", columnsData.getString("DECIMAL_DIGITS"));

                    //是否可为空
                    param.put("nullable", "0".equals(columnsData.getString("NULLABLE")) ? "false" : "true");

                    //字段默认值
                    param.put("columnDef", columnsData.getString("COLUMN_DEF"));

                    //表中的列的索引
                    param.put("ordinalPosition", columnsData.getString("ORDINAL_POSITION"));

                    //备注
                    param.put("remarks", columnsData.getString("REMARKS"));

                    //处理主键
                    if (StringUtil.compare(primaryKeyColumnName, columnName)) {

                        primaryKeyPropertyName = StringUtil.toUpperName(columnName);
                        //主键字段类型
                        primaryKeyPropertyType = ClassUtil.toWrapperNameFromName(propertyType);

                        //是否为主键
                        param.put("isPrimaryKey", "true");

                        //属性类型
                        propertyType = primaryKeyPropertyType;
                    }

                    //属性类型
                    param.put("propertyType", propertyType);
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
                data.put("primaryKeyPropertyName",primaryKeyPropertyName);

                //字段参数
                data.put("columnList", columnList);
                tableInfoList.add(data);

//                //Entity生成器
//                String entityFileName = PropertiesUtil.getProperty("agile.generator.entity_prefix") + className + PropertiesUtil.getProperty("agile.generator.entity_suffix") + ".java";
//                generatorProxy("Service.ftl","./src/main/java/com/agile/mvc/model/entity/",entityFileName,data);
//
                //service生成器
                String ServiceFileName = PropertiesUtil.getProperty("agile.generator.service_prefix") + className + PropertiesUtil.getProperty("agile.generator.service_suffix") + ".java";
                generatorProxy("Service.ftl","./src/main/java/com/agile/mvc/service/",ServiceFileName,data);
//

                //DAO生成器
//                String repositoryFileName = PropertiesUtil.getProperty("agile.generator.repository_prefix") + className + PropertiesUtil.getProperty("agile.generator.repository_suffix") + ".java";
//                generatorProxy("Repository.ftl","./src/main/java/com/agile/mvc/model/dao/",repositoryFileName,data);
            }
            //API生成器
            HashMap<String,Object> map = new HashMap<>();
            map.put("list",tableInfoList);
            generatorProxy("API.ftl","./src/main/webapp/plus/swagger/","api.json",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataBaseUtil.destroyDB();
    }

    /**
     * 生成器引擎
     * @param templateURI 模板地址
     * @param irectory 生成文件目录
     * @param fileName 文件名
     * @param data 数据
     */
    private static void generatorProxy(String templateURI,String directory,String fileName,Map<String, Object> data) throws IOException, TemplateException {
        Template serviceTemp = cfg.getTemplate(templateURI);
        File serviceFile = new File(directory+fileName);
        FileWriter serviceFileFw = new FileWriter(serviceFile);
        BufferedWriter serviceFileBw = new BufferedWriter(serviceFileFw);
        serviceTemp.process(data, serviceFileBw);
        serviceFileBw.flush();
        serviceFileFw.close();
    }
}
