package com.agile.common.security;

import com.agile.common.util.ObjectUtil;
import com.agile.mvc.model.dao.SysRolesRepository;
import com.agile.mvc.model.dao.SysUsersRepository;
import com.agile.mvc.model.entity.SysRolesEntity;
import com.agile.mvc.model.entity.SysUsersEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by 佟盟 on 2017/1/13
 * 从数据库中读入用户的密码，角色信息，是否锁定，账号是否过期等
 */
@Service
public class AgileUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(AgileUserDetailsService.class);
    @Resource
    private SysUsersRepository sysUsersRepository;

    public AgileUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUsersEntity sysUsersEntity = new SysUsersEntity();
        sysUsersEntity.setUsername(username);

        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching();

        //创建实例
        Example<SysUsersEntity> sysUsersEntityExample = Example.of(sysUsersEntity, matcher);

        ///加载用户基本信息
//        SysUsersEntity s = sysUsersRepository.findOne(sysUsersEntityExample);

        //用户信息
        AgileUserDetails agileUserDetails = new AgileUserDetails();
        ObjectUtil.copyProperties(sysUsersEntity,agileUserDetails);

        return agileUserDetails;
    }
}
