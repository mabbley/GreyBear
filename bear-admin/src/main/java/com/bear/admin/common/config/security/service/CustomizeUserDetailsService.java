package com.bear.admin.common.config.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bear.admin.common.config.security.model.CustomizeAdmin;
import com.bear.admin.system.entity.Admin;
import com.bear.admin.system.entity.Role;
import com.bear.admin.system.mapper.AdminMapper;
import com.bear.admin.system.mapper.RoleMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mby on 2019/4/29.
 */
@Service("customizeUserDetailsService")
public class CustomizeUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public CustomizeAdmin loadUserByUsername(String s) throws UsernameNotFoundException {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("login_user", s));
        if(null != admin){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            List<Role> roleList = roleMapper.selectList(new QueryWrapper<Role>().lambda().inSql(Role::getId, "select role_id fromm sys_admin_role where admin_id=" + admin.getId()));
            if(CollectionUtils.isNotEmpty(roleList)){
                roleList.forEach(role -> {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleCode());
                    grantedAuthorities.add(grantedAuthority);
                });
            }
            return CustomizeAdmin.create(admin.getId(),admin.getOrgId(),admin.getLoginUser(),admin.getLoginPwd(),grantedAuthorities);
        }else{
            throw new UsernameNotFoundException("用户名或密码有误");
        }
    }
}
