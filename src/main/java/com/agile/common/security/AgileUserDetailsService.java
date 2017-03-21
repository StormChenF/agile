package com.agile.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by 佟盟 on 2017/1/13
 */
@Service
public class AgileUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(AgileUserDetailsService.class);
//    @Resource
//    private AdminService adminService;

    public AgileUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AgileUserDetails userDetails = new AgileUserDetails();
//        userDetails.getUsername(username);
//
//        ///加载用户基本信息
//        AdminModel adminModel = adminService.getAdminByAdminId(username);
//        try {
//            BeansUtil.copyProperties(userDetails, adminModel);
//        } catch (IllegalAccessException e) {
//            logger.error("用户信息复制到userDetails出错",e);
//        } catch (InvocationTargetException e) {
//            logger.error("用户信息复制到userDetails出错",e);
//        } catch (NoSuchMethodException e) {
//            logger.error("用户信息复制到userDetails出错",e);
//        }
//        //加载权限信息
//        List<AdminRoleGrantedAuthority> authorities = this.adminService.getAuthorityByUserId(username);
//        if (authorities == null || authorities.size() == 0) {////如果为普通用户
//            if (isCommonUserRequest()) {
//                AdminRoleGrantedAuthority authority =
//                        new AdminRoleGrantedAuthority(AdminRoleGrantedAuthority.ADMIN_ROLE_TYPE_COMMON_USER);
//                userDetails.getAuthorities().add(authority);
//            } else {
//                logger.warn("person authorities is empty, personId is [{}]", username);
//            }
//        }
//        //加载用户权限
//        userDetails.getAuthorities().addAll(authorities);

        ///这个就是权限系统最后的用户信息
        return userDetails;
    }

    private boolean isCommonUserRequest() {
        // TODO Auto-generated method stub
        return true;
    }
}
