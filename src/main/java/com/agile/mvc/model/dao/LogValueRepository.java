package com.agile.mvc.model.dao;

import com.agile.mvc.model.entity.LogValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
* Created by 佟盟
*/
public interface LogValueRepository extends JpaRepository<LogValueEntity,Integer>,QuerydslPredicateExecutor<LogValueEntity> {

}
