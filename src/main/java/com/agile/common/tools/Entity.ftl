package ${entityPackage};

import javax.persistence.*;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "dictionary_data", schema = "agile_db", catalog = "")
public class DictionaryDataEntity {

    /**
     * 新增
     * 地址：http://localhost:8080/agile/${tableName}Service/save
     */
    public RETURN save() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        ${tableName}Repository dao = (${tableName}Repository) FactoryUtil.getBean("${tableName}Repository");
        ${tableName}Entity entity = (${tableName}Entity)ObjectUtil.getObjectFromMap(${tableName}Entity.class, this.getInParam());
        dao.save(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 删除
     * 地址：http://localhost:8080/agile/${tableName}Service/delete
     */
    public RETURN delete(){
        ${tableName}Repository dao = (${tableName}Repository) FactoryUtil.getBean("${tableName}Repository");
        String[] ids = this.getInParam("id").toString().split(",");
        for (String id:ids) {
            dao.delete((Integer) ObjectUtil.cast(Integer.class,id));
        }
        return RETURN.SUCCESS;
    }

    /**
     * 修改
     * 地址：http://localhost:8080/agile/SysUsersService/update
     */
    public RETURN update() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        ${tableName}Repository dao = (${tableName}Repository) FactoryUtil.getBean("${tableName}Repository");
        ${tableName}Entity entity = (${tableName}Entity)ObjectUtil.getObjectFromMap(${tableName}Entity.class, this.getInParam());
        dao.saveAndFlush(entity);
        return RETURN.SUCCESS;
    }

    /**
     * 查询
     * 地址：http://localhost:8080/agile/${tableName}Service/query
     */
    public RETURN query(){
        ${tableName}Repository dao = (${tableName}Repository) FactoryUtil.getBean("${tableName}Repository");
        this.setOutParam("queryList",dao.findAll(this.getPageInfo()));
        return RETURN.SUCCESS;
    }
}
