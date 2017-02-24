package com.agile.mvc.model.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Created by tongmeng on 2017/1/16
 */
@NoRepositoryBean
public interface IDaoFactory<T,ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

}
