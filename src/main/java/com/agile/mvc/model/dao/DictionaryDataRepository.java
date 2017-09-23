package com.agile.mvc.model.dao;

import com.agile.mvc.model.entity.DictionaryDataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by 佟盟
 */
public interface DictionaryDataRepository extends JpaRepository<DictionaryDataEntity,Integer> {
    Page<DictionaryDataEntity> findByName(String name, Pageable pageable);
    @Query(value = "select * from dictionary_data where code = ?1",nativeQuery = true)
    List<DictionaryDataEntity> queryABC(Integer code);

    @Async
    @Query(value = "select code as a,is_fixed as b ,name as c from dictionary_data /*#pageable*/",countQuery = "select count(1) from dictionary_data ",nativeQuery = true)
    Page<HashMap<String,Object>> queryABC2(Pageable pageable);

    @Async
    Future<DictionaryDataEntity> findByCode(Integer code);
}
