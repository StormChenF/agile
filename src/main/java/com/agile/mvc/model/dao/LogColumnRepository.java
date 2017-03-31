package com.agile.mvc.model.dao;

import com.agile.mvc.model.entity.LogColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* Created by 佟盟
*/
public interface LogColumnRepository extends JpaRepository<LogColumnEntity,Integer> ,JpaSpecificationExecutor{

}
