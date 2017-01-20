package com.deepbar.framework.security.login;

import com.deepbar.framework.security.userdetails.UserInfo;

/**
 * Created by josh on 15/8/17.
 */
public interface LoginLogHandler {
    void saveLoginLog(String remoteIp, UserInfo userInfo);
}
