package com.agile.common.properties;

import com.agile.common.annotation.Properties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 佟盟 on 2018/1/5
 */
@Properties(prefix = "agile.redis")
public class RedisConfigProperties {
    private static String pass;
    private static int maxIdle;
    private static int minIdle;
    private static int maxWaitMillis;
    private static boolean testOnReturn;
    private static boolean testOnBorrow;
    private static List<String> host;
    private static List<Integer> port;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public List<String> getHost() {
        return host;
    }

    public void setHost(List<String> host) {
        this.host = host;
    }

    public List<Integer> getPort() {
        return port;
    }

    public void setPort(List<Integer> port) {
        this.port = port;
    }
}
