package com.deepbar.framework.security.access;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Josh
 * Date: 13-10-31
 * Time: 下午3:25
 * To change this template use File | Settings | File Templates.
 */
public class MyAccessDecisionManagerImpl implements AccessDecisionManager {
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null || configAttributes.size() == 0) {
            return;
        }
        for (ConfigAttribute configAttribute : configAttributes) {
            String needRole = configAttribute.getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needRole.equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException(" 没有权限访问");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean supports(ConfigAttribute configAttribute) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean supports(Class<?> aClass) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
