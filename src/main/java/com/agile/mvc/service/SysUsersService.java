package com.agile.mvc.service;

import com.agile.common.base.AbstractBusiness;
import com.agile.common.base.RETURN;
import com.agile.mvc.model.dao.SysUsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Service;

import javax.persistence.QueryHint;
import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tongmeng on 2017/1/18.
 */
@Service
public class SysUsersService extends AbstractBusiness {

    @Autowired
    private SysUsersDao sysUsersDao;

    public RETURN aa(){
        Iterable d = sysUsersDao.findAll(new PageRequest(0,10));
        sysUsersDao.findAll(new PageRequest(0,10));
        sysUsersDao.findAll(new PageRequest(0,10));
        this.getOutParam().put("userList",d);
        return RETURN.SUCCESS;
    }
}
