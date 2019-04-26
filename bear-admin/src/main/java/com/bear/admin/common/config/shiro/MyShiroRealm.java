package com.bear.admin.common.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bear.admin.common.config.shiro.service.ShiroLoginService;
import com.bear.admin.common.enums.AdminStatus;
import com.bear.admin.system.entity.Admin;
import com.bear.admin.system.entity.Menu;
import com.bear.admin.system.entity.Role;
import com.bear.admin.system.mapper.AdminMapper;
import com.bear.admin.system.mapper.MenuMapper;
import com.bear.admin.system.mapper.RoleMapper;
import com.bear.common.core.EncryptUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Created by mby on 2019/4/24.
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    private ShiroLoginService shiroLoginService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Admin admin = ShiroUtils.getUserEntity();
        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 管理员拥有所有权限
        if (admin.getLoginUser().trim().toUpperCase().equals("ADMIN".toUpperCase())) {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        } else {

            List<Role> roleList = roleMapper.selectList(new QueryWrapper<Role>().lambda().inSql(Role::getId, "select role_id fromm sys_admin_role where admin_id=" + admin.getId()));
            if (null != roleList && roleList.size() > 0) {
                roleList.forEach(role -> {
                    roles.add(role.getRoleCode());
                });
            }
            List<Menu> menuList = menuMapper.selectList(new QueryWrapper<Menu>().lambda().inSql(Menu::getId, "select menu_id fromm sys_role_menu where role_id in (select role_id fromm sys_admin_role where admin_id="+admin.getId()+")"));
            if (null != menuList && menuList.size() > 0) {
                menuList.forEach(menu -> {
                    menus.addAll(Arrays.asList(menu.getFilter().trim().split(",")));
                });
            }
            info.setRoles(roles);// 角色加入AuthorizationInfo认证对象
            info.setStringPermissions(menus);// 权限加入AuthorizationInfo认证对象
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        Admin admin = shiroLoginService.login(token.getUsername(), new String(token.getPassword()));
        if(admin == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(admin, new String(token.getPassword()), getName());
        return info;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
