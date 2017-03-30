package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by mydeathtrial on 2017/3/20.
 */
@Entity
@Table(name = "sys_bt_authorities_resources", schema = "agile_db", catalog = "")
public class SysBtAuthoritiesResourcesEntity {
    private int id;
    private int resourceId;
    private int authorityId;
    private int sysBtAuthoritiesResourcesId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "SYS_BT_AUTHORITIES_RESOURCES_ID")
    public int getSysBtAuthoritiesResourcesId() {
        return sysBtAuthoritiesResourcesId;
    }

    public void setSysBtAuthoritiesResourcesId(int sysBtAuthoritiesResourcesId) {
        this.sysBtAuthoritiesResourcesId = sysBtAuthoritiesResourcesId;
    }

    @Basic
    @Column(name = "RESOURCE_ID", nullable = false)
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "AUTHORITY_ID", nullable = false)
    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysBtAuthoritiesResourcesEntity that = (SysBtAuthoritiesResourcesEntity) o;

        if (id != that.id) return false;
        if (resourceId != that.resourceId) return false;
        if (authorityId != that.authorityId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + resourceId;
        result = 31 * result + authorityId;
        return result;
    }
}
