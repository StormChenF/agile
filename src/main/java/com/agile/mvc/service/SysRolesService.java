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

        SysAuthoritiesEntity s = new SysAuthoritiesEntity();
        s.setAuthorityDesc("1111111111");
        s.setAuthorityMark("2222222222");
        s.setAuthorityName("3333333333");
        s.setEnable(true);
        s.setIssys(true);
        s.setMessage("4444444444");
        s.setModuleId("12");
        Object a = dao.save(s);
        Iterable d = dao.findAll(new PageRequest(0,10));
        this.setOutParam("b",d);
        return RETURN.SUCCESS;
    }
}
