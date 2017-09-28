package com.agile.mvc.model.dao;

import com.agile.mvc.model.entity.SysAuthoritiesEntity;
import com.agile.mvc.model.entity.SysUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
* Created by 佟盟
*/
public interface SysUsersRepository extends JpaRepository<SysUsersEntity,Integer> {
    SysUsersEntity findByName(String name);

    @Query(nativeQuery = true,value =
            "SELECT" +
                    "sys_authorities.SYS_AUTHORITY_ID," +
                    "sys_authorities.AUTHORITY_MARK," +
                    "sys_authorities.AUTHORITY_NAME," +
                    "sys_authorities.AUTHORITY_DESC," +
                    "sys_authorities.MESSAGE," +
                    "sys_authorities.`ENABLE`," +
                    "sys_authorities.ISSYS," +
                    "sys_authorities.MODULE_ID" +
                    "FROM sys_users" +
                    "LEFT JOIN sys_bt_users_roles ON sys_users.SYS_USERS_ID = sys_bt_users_roles.USER_ID" +
                    "LEFT JOIN sys_roles ON sys_roles.SYS_ROLES_ID = sys_bt_users_roles.ROLE_ID" +
                    "LEFT JOIN sys_bt_roles_authorities ON sys_roles.SYS_ROLES_ID = sys_bt_roles_authorities.ROLE_ID" +
                    "LEFT JOIN sys_authorities ON sys_authorities.SYS_AUTHORITY_ID = sys_bt_roles_authorities.AUTHORITY_ID" +
                    "WHERE sys_users.USERNAME = ?1")
    Set<SysAuthoritiesEntity> querySysAuthoritiesByName(String name);
}
