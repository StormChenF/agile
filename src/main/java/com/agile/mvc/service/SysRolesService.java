package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.mvc.model.dao.SysAuthoritiesEntityRepository;
import com.agile.mvc.model.entity.SysAuthoritiesEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by tongmeng on 2017/3/15
 */
@Service
public class SysRolesService extends AgileMainService {
    public RETURN bb(){
        SysAuthoritiesEntityRepository dao = (SysAuthoritiesEntityRepository) FactoryUtil.getBean("SysAuthoritiesEntityRepository");
        Iterable b = dao.findAll(new PageRequest(0,10));
        this.setOutParam("b",b);
        return RETURN.SUCCESS;
    }
}
