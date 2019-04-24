package com.bear;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bear.admin.common.base.entity.ParamMap;
import com.bear.admin.common.base.entity.SearchParam;
import com.bear.admin.common.enums.AdminStatus;
import com.bear.admin.system.entity.Admin;
import com.bear.admin.system.service.AdminService;
import com.bear.common.core.ReflectionUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class AdminTest extends BaseTest{

    @Autowired
    private AdminService adminService;

    public void insert(){
        Admin admin = new Admin();
        ReflectionUtils.RandData(admin);
        log.info("insert start:{}", JSON.toJSONString(admin));
        adminService.save(admin);
        log.info("insert end:{}", JSON.toJSONString(admin));
    }
    public void insertBatch(){
        List<Admin> adminList = Lists.newArrayList();
        for(int i=0;i<100;i++){
            Admin admin = new Admin();
            ReflectionUtils.RandData(admin);
            adminList.add(admin);
        }
        log.info("insertBatch start:{}", JSON.toJSONString(adminList));
        adminService.saveBatch(adminList);
        log.info("insertBatch end:{}", JSON.toJSONString(adminList));
    }

    public void update(){
        Admin admin = new Admin();
        admin.setId(568436663542890496L);
        admin.setEmail("9999999@163.com");
        adminService.updateById(admin);
    }

    public void updateBatch(){
        Admin admin = new Admin();
        admin.setStatus(AdminStatus.DISABLE);
        adminService.updateParam(admin,ParamMap.init().like("login_user","y"));
    }

    public void delete(){
        adminService.removeById(568436663542890496L);
    }
    public void deleteBatch(){
        adminService.removeByIds(Lists.newArrayList(568436663538696192L,568436663534501888L));
    }
    public void findPage(){
        SearchParam searchParam = new SearchParam();
        searchParam.setPageNo(0);
        searchParam.setPageSize(2);
        IPage<Admin> page = adminService.findPage(searchParam);
        log.info("findPage :{}", JSON.toJSONString(page));
    }
    public void findById(){
        Admin byId = adminService.getById(568402386780438528L);
        log.info("findById :{}", JSON.toJSONString(byId));
    }
    @Test
    public void findParam(){
        List<Admin> byParam = adminService.findByParam(ParamMap.init().eq("login_user", "admin"));
        log.info("findParam :{}", JSON.toJSONString(byParam));
    }
}
