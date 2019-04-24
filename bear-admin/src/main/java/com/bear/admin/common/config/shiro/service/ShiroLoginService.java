package com.bear.admin.common.config.shiro.service;

import com.bear.admin.system.entity.Admin;

/**
 * Created by mby on 2019/4/24.
 */
public interface ShiroLoginService {

    Admin login(String loginUser, String loginPwd);
}
