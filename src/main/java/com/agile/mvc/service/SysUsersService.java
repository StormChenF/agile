package com.agile.mvc.service;

import com.agile.common.base.AgileMainService;
import com.agile.common.base.RETURN;
import com.agile.common.util.FactoryUtil;
import com.agile.mvc.model.dao.SysAuthoritiesEntityRepository;
import com.agile.mvc.model.entity.SysAuthoritiesEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by 佟盟 on 2017/1/18
 */
@Service
public class SysUsersService extends AgileMainService {

    public RETURN aa(){
        SysAuthoritiesEntityRepository dao = (SysAuthoritiesEntityRepository) FactoryUtil.getBean("SysAuthoritiesEntityRepository");

        SysAuthoritiesEntity s = new SysAuthoritiesEntity();
        s.setAuthorityDesc("1111111111");
        s.setAuthorityMark("2222222222");
        s.setAuthorityName("3333333333");
        s.setEnable(true);
        s.setIssys(true);
        s.setMessage("4444444444");
        s.setModuleId("12");
        dao.save(s);

        return RETURN.SUCCESS;
    }
}
