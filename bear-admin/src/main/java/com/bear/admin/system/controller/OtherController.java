package com.bear.admin.system.controller;

import com.unicorn.admin.common.cache.AdminMenuCache;
import com.unicorn.admin.common.config.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

/**
 * Created by mby on 2019/2/12.
 */
@Controller
public class OtherController {

    @Autowired
    private AdminMenuCache adminMenuCache;

    @RequestMapping("index")
    public String index(ModelMap mmap) throws ExecutionException {
        mmap.put("menus", adminMenuCache.get(ShiroUtils.getAdminId()));
        mmap.put("admin", ShiroUtils.getAdmin());
        return "index";
    }


}
