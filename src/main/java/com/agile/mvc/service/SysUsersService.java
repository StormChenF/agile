package com.agile.mvc.service;

import com.agile.common.base.AbstractBusiness;
import com.agile.common.base.RETURN;
import com.agile.mvc.model.dao.IDaoFactory;
import com.agile.mvc.model.entity.SysUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by tongmeng on 2017/1/18
 */
@Service
public class SysUsersService extends AbstractBusiness {
    private final IDaoFactory<SysUsersEntity,Integer> daoFactory;


    @Autowired
    public SysUsersService(IDaoFactory<SysUsersEntity, Integer> daoFactory) {
        this.daoFactory = daoFactory;
    }


    public RETURN aa(){
        Iterable d = daoFactory.findAll(new PageRequest(0,10));
        this.getOutParam().put("userList",d);
        return RETURN.SUCCESS;
    }
}
