package com.woniu.yoga.user.vo;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

import java.io.Serializable;

/**
 * @Description: java类作用描述PhoneToken
 * @Author: lxy
 * @time: 2019/4/23 15:33
 */
public class PhoneToken implements HostAuthenticationToken, RememberMeAuthenticationToken , Serializable {
    private String userPhone;
    private boolean rememberMe;
    private String host;
    public Object getPrincipal() {
        return userPhone;
    }
    public Object getCredentials() {
        return userPhone;
    }
    public PhoneToken() {
        this.rememberMe = false;
    }

    public PhoneToken(String userPhone) {
        this(userPhone, false, null);
    }

    public PhoneToken(String userPhone, boolean rememberMe) {
        this(userPhone, rememberMe, null);
    }

    public PhoneToken(String userPhone, boolean rememberMe, String host) {
        this.userPhone = userPhone;
        this.rememberMe = rememberMe;
        this.host = host;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }

}
