package com.bear;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bear.admin.common.base.entity.ParamMap;
import com.bear.admin.common.base.entity.SearchParam;
import com.bear.admin.common.enums.AdminStatus;
import com.bear.admin.system.entity.Admin;
import com.bear.admin.system.entity.Menu;
import com.bear.admin.system.service.AdminService;
import com.bear.admin.system.service.MenuService;
import com.bear.common.core.ReflectionUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class MenuTest extends BaseTest{

    @Autowired
    private MenuService menuService;


    @Test
    public void insertBatch(){
        List<Menu> adminList = Lists.newArrayList();
        for(int i=0;i<100;i++){
            Menu menu = new Menu();
            ReflectionUtils.RandData(menu);
            adminList.add(menu);
        }
        log.info("insertBatch start:{}", JSON.toJSONString(adminList));
        menuService.saveBatch(adminList);
        log.info("insertBatch end:{}", JSON.toJSONString(adminList));
    }

}
