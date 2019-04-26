package com.bear.admin.common.config.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bear.admin.common.config.shiro.service.ShiroLoginService;
import com.bear.admin.common.enums.AdminStatus;
import com.bear.admin.system.entity.Admin;
import com.bear.admin.system.mapper.AdminMapper;
import com.bear.common.core.EncryptUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mby on 2019/4/24.
 */
@Service
public class ShiroLoginServiceImpl implements ShiroLoginService {


    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> loginRecordCache;
    private Cache<String, Long> loginPwdCountCache;

    private static final Integer maxRetryCount = 3;

    @PostConstruct
    public void init() {
        loginRecordCache = cacheManager.getCache("loginRecordCache");
        loginPwdCountCache = cacheManager.getCache("loginPwdCountCache");
    }

    @Override
    public Admin login(String loginUser, String loginPwd) {

        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("login_user", loginUser));
        //账号不存在
        if(admin == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        //账号锁定
        if(admin.getStatus().equals(AdminStatus.LOCKING)){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        if(loginRecordCache == null){
            loginRecordCache = cacheManager.getCache("loginRecordCache");
        }
        AtomicInteger retryCount = loginRecordCache.get(loginUser);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginUser, retryCount);
        }

        if(retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue()){
            loginPwdCountCache.put(loginUser, System.currentTimeMillis());
            throw new UnknownAccountException("账号或密码错误次数过多,请十分钟后再试");
        }
        AtomicInteger finalRetryCount = retryCount;
        if(!EncryptUtils.hmacSha256(loginPwd).toUpperCase().equals(admin.getLoginPwd().toUpperCase())){
            loginRecordCache.put(loginUser, finalRetryCount);
            throw new UnknownAccountException("账号或密码不正确");
        }
        loginRecordCache.remove(loginUser);
        return admin;
    }
}
