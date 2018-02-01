package com.agile.common.properties;

import com.agile.common.annotation.Properties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 佟盟 on 2018/1/11
 */
@Properties(prefix = "agile")
public class DBConfigProperties {
    private List<DruidConfigProperty> druid;
    private List<JPAConfigProperty> jpa;

    public List<DruidConfigProperty> getDruid() {
        return druid;
    }

    public void setDruid(List<DruidConfigProperty> druid) {
        this.druid = druid;
    }

    public List<JPAConfigProperty> getJpa() {
        return jpa;
    }

    public void setJpa(List<JPAConfigProperty> jpa) {
        this.jpa = jpa;
    }
}