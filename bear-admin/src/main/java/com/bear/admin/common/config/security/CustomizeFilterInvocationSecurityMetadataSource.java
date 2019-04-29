package com.bear.admin.common.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bear.admin.system.entity.Menu;
import com.bear.admin.system.entity.Role;
import com.bear.admin.system.mapper.MenuMapper;
import com.bear.admin.system.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by mby on 2019/4/29.
 */
@Slf4j
@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
     */
    private HashMap<String, Collection<ConfigAttribute>> resourceMap = null;

    public void loadResourceDefine() {
        resourceMap = new HashMap<>();
        List<Role> roleList = roleMapper.selectList(new QueryWrapper<Role>());
        if(CollectionUtils.isNotEmpty(roleList)){
            for(Role role:roleList){
                ConfigAttribute ca = new SecurityConfig(role.getRoleCode());
                List<Menu> menuList = menuMapper.selectList(new QueryWrapper<Menu>().lambda().inSql(Menu::getId, "select menu_id fromm sys_role_menu where role_id =" + role.getId() + ""));
                if(CollectionUtils.isNotEmpty(menuList)){
                    for(Menu menu:menuList){
                        String url = menu.getUrl();
                        if (resourceMap.containsKey(url)) {
                            Collection<ConfigAttribute> value = resourceMap.get(url);
                            value.add(ca);
                            resourceMap.put(url, value);
                        } else {
                            Collection<ConfigAttribute> atts = new ArrayList<>();
                            atts.add(ca);
                            resourceMap.put(url, atts);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (resourceMap == null) {
            loadResourceDefine();
        }
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            String url = entry.getKey();
            if (new AntPathRequestMatcher(url).matches(request)) {
                return resourceMap.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
