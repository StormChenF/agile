package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.mvc.model.dao.SysAuthoritiesEntityRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by 佟盟 on 2017/1/18
 */
@Service
public class SysUsersService extends AgileMainService {

    public RETURN aa(){
        SysAuthoritiesEntityRepository agileRepository = (SysAuthoritiesEntityRepository) FactoryUtil.getBean("SysAuthoritiesEntityRepository");
        this.getInParam("a");
        Iterable d = agileRepository.findAll(new PageRequest(0,10));
        this.setOutParam("a",d);
        return RETURN.SUCCESS;
    }
}
