package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.mvc.model.dao.SysAuthoritiesEntityRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by mydeathtrial on 2017/3/15
 */
@Service
public class SysRolesService extends AgileMainService {
    public RETURN bb(){
        SysAuthoritiesEntityRepository agileRepository = (SysAuthoritiesEntityRepository) FactoryUtil.getBean("SysAuthoritiesEntityRepository");
        Iterable d = agileRepository.findAll(new PageRequest(0,10));
        this.setOutParam("b",d);
        return RETURN.SUCCESS;
    }
}
