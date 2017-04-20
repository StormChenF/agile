package com.agile.mvc.model.dao;

import com.agile.mvc.model.entity.DictionaryDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
* Created by 佟盟
*/
public interface DictionaryDataRepository extends JpaRepository<DictionaryDataEntity,Integer>,QuerydslPredicateExecutor<DictionaryDataEntity> {

}
