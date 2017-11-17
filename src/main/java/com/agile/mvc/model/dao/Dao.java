package com.agile.mvc.model.dao;

import com.agile.common.exception.NoSuchIDException;
import com.agile.common.util.ArrayUtil;
import com.agile.common.util.ObjectUtil;
import com.agile.common.util.StringUtil;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by 佟盟 on 2017/11/15
 */
@Lazy
@Component
public class Dao {
    private static Map<String,Object> map = new HashMap<>();

    @PersistenceContext
    private EntityManager entityManager ;
    private <T,ID>JpaRepository getRepository(Class<T> tableClass) throws NoSuchIDException {
        Field field = getIdField(tableClass);
        if(ObjectUtil.isEmpty(field)){
            throw new NoSuchIDException();
        }else {
            @SuppressWarnings("unchecked")
            Class<ID> idClass = (Class<ID>) field.getType();
            JpaRepository repository = (JpaRepository) map.get(tableClass.getName());
            if(ObjectUtil.isEmpty(repository)){
                repository = new SimpleJpaRepository<T,ID>(tableClass,entityManager);
                map.put(tableClass.getName(),repository);
            }
            return repository;
        }
    }

    /**
     * 获取EntityManager，操作jpa api的入口
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * 保存
     * @param o ORM对象
     */
    public void save(Object o){
        this.entityManager.persist(o);
    }

    /**
     * 保存并刷新
     * @param tableClass ORM对象类型
     */
    @SuppressWarnings("unchecked")
    public <T>Object save(Class<T> tableClass,Object o){
        try {
            return getRepository(tableClass).save(o);
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存并刷新
     * @param tableClass ORM对象类型
     */
    @SuppressWarnings("unchecked")
    public <T>Object saveAndFlush(Class<T> tableClass,Object o){
        try {
            return getRepository(tableClass).saveAndFlush(o);
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断数据是否存在
     * @param tableClass ORM对象类型
     * @param id 数据主键
     */
    @SuppressWarnings("unchecked")
    public <T>Object existsById(Class<T> tableClass,Object id){
        try {
            return getRepository(tableClass).existsById(id);
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 刷新表
     * @param tableClass ORM对象类型
     */
    @SuppressWarnings("unchecked")
    public <T>Object flush(Class<T> tableClass){
        try {
            getRepository(tableClass).flush();
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新
     * @param o ORM对象
     */
    public <T>T update(T o){
        return this.entityManager.merge(o);
    }

    /**
     * 删除
     * @param o ORM对象
     */
    public void delete(Object o){
        this.entityManager.remove(o);
    }

    /**
     * 删除
     * @param tableClass ORM对象类型
     * @param id 删除的主键标识
     */
    @SuppressWarnings("unchecked")
    public <T>void deleteById(Class<T> tableClass,Object id){
        try {
            getRepository(tableClass).deleteById(id);
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除全部(逐一删除)
     * @param tableClass ORM对象类型
     */
    @SuppressWarnings("unchecked")
    public <T>void deleteAll(Class<T> tableClass){
        try {
            getRepository(tableClass).deleteAll();
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除全部(一次性删除)
     * @param tableClass ORM对象类型
     */
    @SuppressWarnings("unchecked")
    public <T>void deleteAllInBatch(Class<T> tableClass){
        try {
            getRepository(tableClass).deleteAllInBatch();
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
    }

    /**
     * 主键数组转换ORM对象列表
     * @param tableClass ORM类型
     * @param ids 主键数组
     */
    private <T,ID>List createObjectList(Class<T> tableClass,ID[] ids){
        ArrayList list = new ArrayList();
        Field idField = getIdField(tableClass);
        if(ObjectUtil.isEmpty(idField)){
            new NoSuchIDException().printStackTrace();
            return null;
        }
        for (int i = 0 ; i < ids.length;i++){
            try {
                Object instance = tableClass.newInstance();
                idField.setAccessible(true);
                idField.set(instance,ObjectUtil.cast(idField.getType(),ids[i]));
                list.add(instance);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    /**
     * 获取ORM中的主键字段
     * @param clazz ORM类
     */
    private Field getIdField(Class clazz){
        Method[] methods =  clazz.getDeclaredMethods();
        for (int i = 0 ; i < methods.length;i++){
            Method method = methods[i];
            method.setAccessible(true);
            Id id = method.getAnnotation(Id.class);
            if(ObjectUtil.isEmpty(id))continue;
            try {
                return clazz.getDeclaredField(StringUtil.toLowerName(method.getName().replace("get","")));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 部分删除
     * @param tableClass ORM对象类型
     */
    @SuppressWarnings("unchecked")
    public <T,ID>void deleteAll(Class<T> tableClass,ID[] ids){
        if(ArrayUtil.isEmpty(ids) || ids.length<1)return;
        List list = createObjectList(tableClass,ids);
        if(!ObjectUtil.isEmpty(list) && list.size()>0){
            try {
                getRepository(tableClass).deleteAll(list);
            } catch (NoSuchIDException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 部分删除(一次性删除)
     * @param tableClass ORM对象类型
     */
    @SuppressWarnings("unchecked")
    public <T,ID>void deleteInBatch(Class<T> tableClass,ID[] ids){
        if(ArrayUtil.isEmpty(ids) || ids.length<1)return;
        List list = createObjectList(tableClass, ids);
        if(!ObjectUtil.isEmpty(list) && list.size()>0){
            try {
                getRepository(tableClass).deleteInBatch(list);
            } catch (NoSuchIDException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 部分删除(一次性删除)
     * @param list 需要删除的对象列表
     */
    @SuppressWarnings("unchecked")
    public <T>void deleteInBatch(Iterable<T> list){
        Iterator<T> iterator = list.iterator();
        if(iterator.hasNext()){
            T obj = iterator.next();
            try {
                getRepository(obj.getClass()).deleteInBatch(list);
            } catch (NoSuchIDException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询单条
     */
    public <T>T findOne(Class<T> clazz,Object id){
        return this.entityManager.find(clazz,id);
    }

    /**
     * 查询列表
     * @param sql sql
     * @param clazz 返回ORM类型列表
     */
    @SuppressWarnings("unchecked")
    public <T>T findOne(String sql, Class<T> clazz){
        Query query = this.entityManager.createNativeQuery(sql, clazz);
        List list = query.getResultList();
        if (ObjectUtil.isEmpty(list) || list.size()<1)return null;
        return (T) query.getResultList().get(0);
    }

    /**
     * 查询列表
     * @param sql sql
     * @param clazz 返回ORM类型列表
     */
    @SuppressWarnings("unchecked")
    public <T>List<T> findAll(String sql, Class<T> clazz){
        Query query = this.entityManager.createNativeQuery(sql, clazz);
        return query.getResultList();
    }

    /**
     * 查询列表
     * @param sql sql
     */
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> findAll(String sql){
        Query query = this.entityManager.createNativeQuery(sql);
        query.unwrap(org.hibernate.Query.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    /**
     * 查询列表
     */
    @SuppressWarnings("unchecked")
    public <T,ID>List<T> findAll(Class<T> tableClass,Iterable<ID> ids){
        try {
            return getRepository(tableClass).findAllById(ids);
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询列表
     */
    @SuppressWarnings("unchecked")
    public <T,ID>List<T> findAll(Class<T> tableClass,ID[] ids){
        try {
            return getRepository(tableClass).findAllById(ArrayUtil.asList(ids));
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询列表
     */
    @SuppressWarnings("unchecked")
    public <T>Page<T> findAll(Class<T> tableClass,int page, int size){
        try {
            return getRepository(tableClass).findAll(PageRequest.of(page,size));
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询表总数
     */
    @SuppressWarnings("unchecked")
    public long count(Class tableClass){
        try {
            return getRepository(tableClass).count();
        } catch (NoSuchIDException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
