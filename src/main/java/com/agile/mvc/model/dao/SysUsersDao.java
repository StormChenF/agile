package com.agile.mvc.model.dao;

import com.agile.mvc.model.entity.SysUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Created by tongmeng on 2017/1/16.
 */
public interface SysUsersDao extends PagingAndSortingRepository<SysUsersEntity,Integer> {

}
