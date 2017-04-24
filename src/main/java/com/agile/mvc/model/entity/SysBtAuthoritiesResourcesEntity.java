package com.agile.mvc.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_bt_authorities_resources",  catalog = "agile_db")
public class SysBtAuthoritiesResourcesEntity implements Serializable {
    //序列
    private static final long serialVersionUID = 1L;
    //唯一标识
    private Integer sysBtAuthoritiesResourcesId;
    //资源唯一标识
    private Integer resourceId;
    //权限唯一标识
    private Integer authorityId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_BT_AUTHORITIES_RESOURCES_ID" , nullable  = true)
    public Integer getSysBtAuthoritiesResourcesId() {
        return sysBtAuthoritiesResourcesId;
    }

    public void setsysBtAuthoritiesResourcesId(int sysBtAuthoritiesResourcesId) {
        this.sysBtAuthoritiesResourcesId = sysBtAuthoritiesResourcesId;
    }

    @Basic
    @Column(name = "RESOURCE_ID" , nullable  = true)
    public Integer getResourceId() {
        return resourceId;
    }

    public void setresourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "AUTHORITY_ID" , nullable  = true)
    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setauthorityId(int authorityId) {
        this.authorityId = authorityId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysBtAuthoritiesResourcesEntity that = (SysBtAuthoritiesResourcesEntity) o;

        return Objects.equals(sysBtAuthoritiesResourcesId, that.sysBtAuthoritiesResourcesId) &&Objects.equals(resourceId, that.resourceId) &&Objects.equals(authorityId, that.authorityId) ;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + sysBtAuthoritiesResourcesId;
        result = 31 * result + resourceId;
        result = 31 * result + authorityId;
        return result;
    }
}
