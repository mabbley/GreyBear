package com.bear.admin.common.config.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by mby on 2019/4/29.
 */
@Slf4j
@Component
public class CustomizeAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if(CollectionUtils.isEmpty(collection)) {
            return;
        }
        for(Iterator<ConfigAttribute> iter = collection.iterator(); iter.hasNext(); ){
            ConfigAttribute configAttribute = iter.next();
            String attribute = configAttribute.getAttribute();
            for(GrantedAuthority ga : authentication.getAuthorities()) {
                if(attribute.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("没有权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
