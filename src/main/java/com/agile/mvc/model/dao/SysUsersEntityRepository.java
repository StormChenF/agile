package com.agile.mvc.model.dao;

import com.agile.mvc.model.entity.SysUsersEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
* Created by 佟盟 on 2017/1/16
*/
public interface SysUsersEntityRepository extends PagingAndSortingRepository<SysUsersEntity,Integer> {

}