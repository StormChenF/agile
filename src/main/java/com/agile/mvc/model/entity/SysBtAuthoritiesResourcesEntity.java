package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
* Created by 佟盟
*/
@Entity
@Table(name = "sys_bt_authorities_resources",  catalog = "agile_db")
public class SysBtAuthoritiesResourcesEntity {

    private Integer sysBtAuthoritiesResourcesId;
    private Integer resourceId;
    private Integer authorityId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_BT_AUTHORITIES_RESOURCES_ID")
    public Integer getSysBtAuthoritiesResourcesId() {
        return sysBtAuthoritiesResourcesId;
    }

    public void setsysBtAuthoritiesResourcesId(int sysBtAuthoritiesResourcesId) {
        this.sysBtAuthoritiesResourcesId = sysBtAuthoritiesResourcesId;
    }

    @Basic
    @Column(name = "RESOURCE_ID")
    public Integer getResourceId() {
        return resourceId;
    }

    public void setresourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "AUTHORITY_ID")
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

        if (sysBtAuthoritiesResourcesId != that.sysBtAuthoritiesResourcesId) return false;
        if (resourceId != that.resourceId) return false;
        if (authorityId != that.authorityId) return false;
        return true;
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
