package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.BeanUtil;
import com.agile.mvc.model.dao.AgileRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by 佟盟 on 2017/1/18
 */
@Service
public class SysUsersService extends AgileMainService {

    public RETURN aa(){
        AgileRepository agileRepository = (AgileRepository)BeanUtil.getBean("agileRepository");
        this.getInParam("a");
        Iterable d = agileRepository.findAll(new PageRequest(0,10));
        this.setOutParam("a",d);
        return RETURN.SUCCESS;
    }
}
