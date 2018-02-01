package com.agile.common.properties;

import com.agile.common.annotation.Properties;

/**
 * Created by 佟盟 on 2018/2/1
 */
@Properties(prefix = "agile.security")
public class SecurityProperties {
    private String loginUrl = "/signService/signIn";
    private String loginOutUrl = "/signService/signOut";
    private String loginOutSuccessUrl = "/signService/successSignOut";
    private String notLoginUrl = "/signService/notSignIn";
    private String invalidSessionUrl = "/signService/invalidSession";
    private String expiredSessionUrl = "/signService/expiredSession";
    private String successUrl = "/signService/homepage";
    private String failureUrl = "/signService/errorSignIn";
    private String accessDenied = "/signService/accessDenied";
    private String csrfAllowUrl;
    private String csrfAllowMethods = "GET,HEAD,TRACE,OPTIONS";
    private String verificationCode = "verification";

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLoginOutUrl() {
        return loginOutUrl;
    }

    public void setLoginOutUrl(String loginOutUrl) {
        this.loginOutUrl = loginOutUrl;
    }

    public String getLoginOutSuccessUrl() {
        return loginOutSuccessUrl;
    }

    public void setLoginOutSuccessUrl(String loginOutSuccessUrl) {
        this.loginOutSuccessUrl = loginOutSuccessUrl;
    }

    public String getNotLoginUrl() {
        return notLoginUrl;
    }

    public void setNotLoginUrl(String notLoginUrl) {
        this.notLoginUrl = notLoginUrl;
    }

    public String getInvalidSessionUrl() {
        return invalidSessionUrl;
    }

    public void setInvalidSessionUrl(String invalidSessionUrl) {
        this.invalidSessionUrl = invalidSessionUrl;
    }

    public String getExpiredSessionUrl() {
        return expiredSessionUrl;
    }

    public void setExpiredSessionUrl(String expiredSessionUrl) {
        this.expiredSessionUrl = expiredSessionUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    public String getAccessDenied() {
        return accessDenied;
    }

    public void setAccessDenied(String accessDenied) {
        this.accessDenied = accessDenied;
    }

    public String getCsrfAllowUrl() {
        return csrfAllowUrl;
    }

    public void setCsrfAllowUrl(String csrfAllowUrl) {
        this.csrfAllowUrl = csrfAllowUrl;
    }

    public String getCsrfAllowMethods() {
        return csrfAllowMethods;
    }

    public void setCsrfAllowMethods(String csrfAllowMethods) {
        this.csrfAllowMethods = csrfAllowMethods;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
