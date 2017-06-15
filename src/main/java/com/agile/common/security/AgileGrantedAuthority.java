package com.agile.common.security;

import com.agile.mvc.model.entity.SysAuthoritiesEntity;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by 佟盟 on 2017/6/15
 */
public class AgileGrantedAuthority extends SysAuthoritiesEntity implements GrantedAuthority {
    private static final long serialVersionUID = 1L;

    @Override
    public String getAuthority() {
        return getAuthorityName();
    }
}
