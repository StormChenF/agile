package com.agile.mvc.service;

import com.agile.common.base.AbstractBusiness;
import com.agile.common.base.RETURN;
import com.agile.mvc.model.dao.AgileRepository;
import com.agile.mvc.model.entity.SysUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by 佟盟 on 2017/1/18
 */
@Service
public class SysUsersService extends AbstractBusiness {
    @Autowired
    private AgileRepository agileRepository;

    public RETURN aa(){

        this.getInParam("a");
        Iterable d = agileRepository.findAll(new PageRequest(0,10));
        this.setOutParam("a",d);
        return RETURN.SUCCESS;
    }
}
