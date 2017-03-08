package com.agile.mvc.model.entity;

import javax.persistence.*;

/**
 * Created by 佟盟 on 2017/1/17.
 */
@Entity
@Table(name = "sys_bt_authorities_resources", schema = "agile_db", catalog = "")
public class SysBtAuthoritiesResourcesEntity {
    private Integer id;
    private Integer resourceId;
    private Integer authorityId;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RESOURCE_ID", nullable = false)
    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "AUTHORITY_ID", nullable = false)
    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysBtAuthoritiesResourcesEntity that = (SysBtAuthoritiesResourcesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (resourceId != null ? !resourceId.equals(that.resourceId) : that.resourceId != null) return false;
        if (authorityId != null ? !authorityId.equals(that.authorityId) : that.authorityId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (resourceId != null ? resourceId.hashCode() : 0);
        result = 31 * result + (authorityId != null ? authorityId.hashCode() : 0);
        return result;
    }
}
