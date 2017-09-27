package com.agile.common.security;

import com.agile.mvc.model.dao.SysUsersRepository;
import com.agile.mvc.model.entity.SysAuthoritiesEntity;
import com.agile.mvc.model.entity.SysUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Set;

/**
 * Created by 佟盟 on 2017/1/13
 * 从数据库中读入用户的密码，角色信息，是否锁定，账号是否过期等
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUsersRepository sysUsersRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUsersEntity user = sysUsersRepository.findByName(username);
        Set<SysAuthoritiesEntity> sysAuthoritiesEntities = sysUsersRepository.querySysAuthoritiesByName(username);
        return new SecurityUser(user,sysAuthoritiesEntities);
    }
}
