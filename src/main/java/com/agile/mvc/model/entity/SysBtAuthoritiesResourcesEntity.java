package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/4/17.
 */
@Entity
@Table(name = "sys_bt_authorities_resources", schema = "agile_db", catalog = "")
public class SysBtAuthoritiesResourcesEntity {
    private int resourceId;
    private int authorityId;
    private int sysBtAuthoritiesResourcesId;

    @Basic
    @Column(name = "RESOURCE_ID")
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "AUTHORITY_ID")
    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Id
    @Column(name = "SYS_BT_AUTHORITIES_RESOURCES_ID")
    public int getSysBtAuthoritiesResourcesId() {
        return sysBtAuthoritiesResourcesId;
    }

    public void setSysBtAuthoritiesResourcesId(int sysBtAuthoritiesResourcesId) {
        this.sysBtAuthoritiesResourcesId = sysBtAuthoritiesResourcesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysBtAuthoritiesResourcesEntity that = (SysBtAuthoritiesResourcesEntity) o;

        if (resourceId != that.resourceId) return false;
        if (authorityId != that.authorityId) return false;
        if (sysBtAuthoritiesResourcesId != that.sysBtAuthoritiesResourcesId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resourceId;
        result = 31 * result + authorityId;
        result = 31 * result + sysBtAuthoritiesResourcesId;
        return result;
    }
}
