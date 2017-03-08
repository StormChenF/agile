package com.agile.mvc.model.dao;

import com.agile.mvc.model.entity.SysUsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Created by 佟盟 on 2017/1/16
 */
public interface AgileRepository extends PagingAndSortingRepository<SysUsersEntity,Integer> {

}
