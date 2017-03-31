package com.agile.mvc.model.dao;

import com.agile.mvc.model.entity.SysRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* Created by 佟盟
*/
public interface SysRolesRepository extends JpaRepository<SysRolesEntity,Integer> ,JpaSpecificationExecutor{

}
