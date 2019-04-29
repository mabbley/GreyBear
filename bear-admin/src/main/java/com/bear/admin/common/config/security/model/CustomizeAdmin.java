package com.bear.admin.common.config.security.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by mby on 2019/4/29.
 */
@Getter
@Setter
public class CustomizeAdmin implements UserDetails {

    private Long adminId;
    /**
     *  机构
     */
    private Long orgId;

    /**
     *  账号
     */
    private String loginUser;

    /**
     *  密码
     */
    private String loginPwd;

    /**
     * 权限
     */
    private Collection<? extends GrantedAuthority> authorities;

    public static CustomizeAdmin create(Long adminId,Long orgId,String loginUser,String loginPwd,Collection<? extends GrantedAuthority> authorities){
        CustomizeAdmin customizeAdmin = new CustomizeAdmin();
        customizeAdmin.setAdminId(adminId);
        customizeAdmin.setOrgId(orgId);
        customizeAdmin.setLoginUser(loginUser);
        customizeAdmin.setLoginPwd(loginPwd);
        customizeAdmin.setAuthorities(authorities);
        return customizeAdmin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return loginPwd;
    }

    @Override
    public String getUsername() {
        return loginUser;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
