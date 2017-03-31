package com.agile.mvc.model.dao;

import com.agile.mvc.model.entity.LogTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* Created by 佟盟
*/
public interface LogTableRepository extends JpaRepository<LogTableEntity,Integer> ,JpaSpecificationExecutor{

}
