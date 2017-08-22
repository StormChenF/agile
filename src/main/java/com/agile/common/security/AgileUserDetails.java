package com.agile.common.security;

import com.agile.mvc.model.entity.SysUsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by 佟盟 on 2017/6/15
 */
public class AgileUserDetails extends SysUsersEntity implements UserDetails {

    private static final long serialVersionUID = 1L;
    private List<AgileGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(List<AgileGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return super.getEnabled();
    }
}
