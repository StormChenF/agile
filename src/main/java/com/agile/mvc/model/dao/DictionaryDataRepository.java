package com.agile.mvc.model.dao;

import com.agile.mvc.model.entity.DictionaryDataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* Created by 佟盟
*/
public interface DictionaryDataRepository extends JpaRepository<DictionaryDataEntity,Integer> {
    List<DictionaryDataEntity> findByCode(Integer code);
    Page<DictionaryDataEntity> findByName(String name, Pageable pageable);
    @Query(value = "select * from dictionary_data where code = ?1",nativeQuery = true)
    List<DictionaryDataEntity> queryABC(Integer code);
}
