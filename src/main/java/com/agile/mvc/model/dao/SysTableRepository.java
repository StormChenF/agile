package com.agile.mvc.model.dao;

import com.agile.mvc.model.entity.SysTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
* Created by 佟盟
*/
public interface SysTableRepository extends JpaRepository<SysTableEntity,Integer>,QuerydslPredicateExecutor<SysTableEntity> {

}
